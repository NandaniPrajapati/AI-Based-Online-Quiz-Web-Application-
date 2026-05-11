package Model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name="User")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @OneToMany
    private List<AttemptModel> Attempt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AttemptModel> getQuizId() {
        return Attempt;
    }

    public void setQuizId(List<AttemptModel> quizId) {
        this.Attempt = Attempt;
    }
}
