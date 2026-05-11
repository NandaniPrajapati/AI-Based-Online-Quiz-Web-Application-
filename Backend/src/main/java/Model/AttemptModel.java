package Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Attempt")
public class AttemptModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long attemptId;
    @ManyToOne
    private UserModel user;
    @ManyToOne
    private QuizModel quiz;

    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL)
    private List<AnswerTable> answers;

    public QuizModel getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizModel quiz) {
        this.quiz = quiz;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<AnswerTable> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerTable> answers) {
        this.answers = answers;
    }
}
