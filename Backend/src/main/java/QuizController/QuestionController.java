package QuizController;



import Model.QuestionModel;
import QuizService.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/quiz")

public class QuestionController {

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    private QuestionService questionService;

    // 🔥 Get all questions
    @RequestMapping("/getQuiz")
    public List<QuestionModel> getAllQuestions() {
        return questionService.listAllQuestion();
    }
}
