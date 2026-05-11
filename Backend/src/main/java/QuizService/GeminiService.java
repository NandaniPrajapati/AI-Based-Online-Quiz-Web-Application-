package QuizService;

import DTO.AIQuestionDTO;
import Model.QuizModel;
import QuizRepository.QuestionRepo;
import Model.QuestionModel;
import QuizRepository.QuizRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private QuestionRepo questionRepo;

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private QuizRepo quizRepo;
    @Value("${gemini.api.key}")
    private String apiKey;



    public List<QuestionModel> generateQuiz(String topic, String difficulty) {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();

        // ✅ PROMPT STRUCTURE (VERY IMPORTANT)
        String prompt = "Generate 3 multiple choice questions on " + topic +
                " with difficulty " + difficulty +
                ". Return ONLY a valid JSON array. No explanation. " +
                "Format: [{\"question\":\"...\",\"options\":[\"A\",\"B\",\"C\",\"D\"],\"correctAnswer\":\"...\"}]";

        ObjectMapper mapper = new ObjectMapper();

// Create request JSON properly
        Map<String, String> part = new HashMap<>();
        part.put("text", prompt);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", List.of(part));

        Map<String, Object> request = new HashMap<>();
        request.put("contents", List.of(content));

// Convert to JSON string
        String body;

        try {
            body = mapper.writeValueAsString(request);
        } catch (Exception e) {
            throw new RuntimeException("JSON conversion failed", e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
        );

        // 🔽 PARSE RESPONSE
        List<AIQuestionDTO> dtos = parseResponse(response.getBody());
        System.out.println("qs generating");
        System.out.println("PROMPT: " + prompt);
        System.out.println("BODY: " + body);

        // 🔽 SAVE TO DB

        return saveToDB(dtos, topic, difficulty);
    }

    // ================= PARSE =================
    private List<AIQuestionDTO> parseResponse(String response) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(response);

            String text = root
                    .path("candidates").get(0)
                    .path("content")
                    .path("parts").get(0)
                    .path("text")
                    .asText();

            // Clean unwanted formatting
            text = text.replace("```json", "")
                    .replace("```", "")
                    .trim();

            return mapper.readValue(
                    text,
                    mapper.getTypeFactory().constructCollectionType(List.class, AIQuestionDTO.class)
            );

        } catch (Exception e) {
            throw new RuntimeException("AI parsing failed");
        }
    }

    // ================= SAVE =================
    private List<QuestionModel> saveToDB(List<AIQuestionDTO> dtos, String topic, String difficulty) {

        List<QuestionModel> saved = new ArrayList<>();
        QuizModel quizCreated= new QuizModel();  //will store in quiz table as topic and difficulty

        quizCreated.setTopic(topic);
        quizCreated.setDifficulty(difficulty);
        quizCreated = quizRepo.save(quizCreated);
        for (AIQuestionDTO dto : dtos) {

            QuestionModel q = new QuestionModel();

           // q.setTopic(topic);           // ✅ use method param
           // q.setDifficulty(difficulty); // ✅ use method param

            q.setQuestion(dto.getQuestion());

            List<String> opts = dto.getOptions();

            q.setOptionA(opts.get(0));
            q.setOptionB(opts.get(1));
            q.setOptionC(opts.get(2));
            q.setOptionD(opts.get(3));

            q.setCorrectAnswer(dto.getCorrectAnswer());
            // ✅ Step 4: LINK question → quiz
            q.setQuizCreated(quizCreated);

            questionRepo.save(q);
        }

        return saved;
    }
}