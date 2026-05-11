package QuizService;

import QuizRepository.QuestionRepo;
import Model.QuestionModel;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    public class QuestionService {


        @org.springframework.beans.factory.annotation.Autowired(required=true)
        private QuestionRepo Qr;

        public List<QuestionModel> listAllQuestion() {
            List<QuestionModel> questions = Qr.findAll();
            System.out.println("DB Results: " + questions); // Check if this is empty
            //return questions;
            return Qr.findAll();

        }
}
