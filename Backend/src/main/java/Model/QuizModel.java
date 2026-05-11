package Model;

import jakarta.persistence.*;

@Entity
@Table(name="Quiz")
public class QuizModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String topic;
    @Column
    private String difficulty;

    // 🔗 relation with user
    @ManyToOne
    private TeacherModel createdBy;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public TeacherModel getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(TeacherModel createdBy) {
        this.createdBy = createdBy;
    }
}
