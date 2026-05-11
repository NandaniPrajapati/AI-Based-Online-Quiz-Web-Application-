package QuizRepository;

import Model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<QuestionModel, Long> {

    // 🔍 Get questions by user
    //List<QuestionModel> findByUserId(String userId);

    // 🔍 Get questions by topic
   // List<QuestionModel> findByTopic(String topic);

    // 🔍 Get questions by difficulty
   // List<QuestionModel> findByDifficulty(String difficulty);

    // 🔥 Get questions for specific user + topic
    //List<QuestionModel> findByUserIdAndTopic(String userId, String topic);

}
