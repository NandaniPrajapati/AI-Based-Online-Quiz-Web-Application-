package Model;



import jakarta.persistence.*;


@Entity
@Table(name="question")
public class QuestionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Qsid;
    @Column
    private String question;

    //@ElementCollection
  //  private List<String> options;

    @Column
    private String optionA;
    @Column
    private String optionB;
    @Column
    private String optionC;
    @Column
    private String optionD;

    @Column
   private String correctAnswer;
    //@Column
    //private String difficulty;
    //@Column
    //private String topic;
    @ManyToOne
    private QuizModel quizCreated;

    public QuizModel getQuizCreated() {
        return quizCreated;
    }

    public void setQuizCreated(QuizModel quizCreated) {
        this.quizCreated = quizCreated;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }



    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
}
