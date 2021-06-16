package model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class User extends AbstractEntity<Long, User> {
    @NotNull
    @Email
    @Column(unique = true)
    private String email;                     //  should be valid email address, unique within the system, cannot be changed;
    @NotNull
    @Size(min=2, max=15)
    private String username;                  //  string 2 to 15 characters long - word characters only, unique within the system, cannot be changed;
    @NotNull
    @Size(min=8, max=15)
    private String password;                  //  string 8 to 15 characters long, at least one digit, one capital letter, and one sign different than letter or digit, NOT sent back to the User clients for security reasons;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;                    //  MALE / FEMALE enumeration;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.PLAYER;          //  PLAYER or ADMIN enumeration, PLAYER by default, editable only by Administrators;
    private String picture;                   // f the user (optional) - valid URL, if missing should ne substituted with an avatar according to the gender;
    @Size(min=20, max=250)
    private String description;               // optional) - string 20 - 250 characters long;
    @Size(max=512)
    private String metadata;                  // optional) - string up to 512 characters long, visible and editable only by Administrators;
    private boolean status = true;                   //  boolean - validity status of the user account;
    @OneToMany(mappedBy = "author")
    private List<Quiz> quizzes = new ArrayList<>(); //  list of all Quizzes created by the current User;

    public User() {
    }

    public User(String email, String username, String password, Gender gender) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    public User(String username, String email, String password, Gender gender, String picture, String description, String metadata) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.picture = picture;
        this.description = description;
        this.metadata = metadata;
    }

    public User( String username, String email, String password, Gender gender, Role role, String picture, String description, String metadata) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.picture = picture;
        this.description = description;
        this.metadata = metadata;
    }

    public User(String email, String username, String password, Gender gender, Role role, String picture, String description, String metadata, Date created, Date modified, boolean status) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.picture = picture;
        this.description = description;
        this.metadata = metadata;
        this.status = status;
        super.setCreated(created);
        super.setModified(modified);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void addQuiz(Quiz quiz){
        this.quizzes.add(quiz);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(getId());
        sb.append(", created=").append(getCreated());
        sb.append(", modified=").append(getModified());
        sb.append(", email='").append(email).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", role=").append(role);
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", metadata='").append(metadata).append('\'');
        sb.append(", status=").append(status);
        sb.append(", quizzes=").append(quizzes);
        sb.append('}');
        return sb.toString();
    }
}
