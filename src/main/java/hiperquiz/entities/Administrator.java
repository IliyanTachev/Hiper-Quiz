package hiperquiz.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "ADMIN")
public class Administrator extends User{
    @OneToMany(mappedBy = "blocked_from_admin")
    private List<Quiz> quizzesBlocked = new ArrayList<>(); // for ADMINS only

    public Administrator() {
    }

    public Administrator(List<Quiz> quizzesBlocked) {
        this.quizzesBlocked = quizzesBlocked;
    }

    public List<Quiz> getQuizzesBlocked() {
        return quizzesBlocked;
    }

    public void setQuizzesBlocked(List<Quiz> quizzesBlocked) {
        this.quizzesBlocked = quizzesBlocked;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Administrator{");
        sb.append("email='").append(getEmail()).append('\'');
        sb.append(", username='").append(getUsername()).append('\'');
        sb.append(", password='").append(getPassword()).append('\'');
        sb.append(", gender=").append(getGender());
        sb.append(", role=").append(getRole());
        sb.append(", picture='").append(getPicture()).append('\'');
        sb.append(", description='").append(getDescription()).append('\'');
        sb.append(", metadata='").append(getMetadata()).append('\'');
        sb.append(", status=").append(getStatus());
        sb.append(", quizzes=").append(getQuizzes());
        sb.append('}');
        return sb.toString();
    }
}
