package QuizController;


//@CrossOrigin(origins = "http://localhost:5173")


import Model.QuestionModel;
import QuizService.GeminiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequestMapping("/ai")
@RestController
public class AIController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private GeminiService geminiService;

    // 🔥 Generate AI Questions
    @PostMapping("/generate")
    public List<QuestionModel> generateQuiz(@RequestBody Map<String, String> req) {

        String topic = req.get("topic");
        String difficulty = req.get("difficulty");

        return geminiService.generateQuiz(topic, difficulty);
    }
}
