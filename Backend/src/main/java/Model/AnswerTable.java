package Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Answer")
public class AnswerTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ansid;
    @Column
    private String selectedOption;

    @ManyToOne
    @JoinColumn(name = "attempt_id")
    private AttemptModel attempt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionModel question;

}
