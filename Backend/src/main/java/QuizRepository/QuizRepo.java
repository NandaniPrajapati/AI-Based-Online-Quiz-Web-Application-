package QuizRepository;

import Model.QuestionModel;
import Model.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<QuizModel, Long> {
}
