package model;

import dao.AbstractEntity;

import java.util.List;

public class User extends AbstractEntity<Long, User> {
    private String email;                     //  should be valid email address, unique within the system, cannot be changed;
    private String username;                  //  string 2 to 15 characters long - word characters only, unique within the system, cannot be changed;
    private String password;                  //  string 8 to 15 characters long, at least one digit, one capital letter, and one sign different than letter or digit, NOT sent back to the User clients for security reasons;
    private Gender gender;                    //  MALE / FEMALE enumeration;
    private Role role = Role.PLAYER;          //  PLAYER or ADMIN enumeration, PLAYER by default, editable only by Administrators;
    private String picture;                   // f the user (optional) - valid URL, if missing should ne substituted with an avatar according to the gender;
    private String description;               // optional) - string 20 - 250 characters long;
    private String metadata;                  // optional) - string up to 512 characters long, visible and editable only by Administrators;
    private boolean status;                   //  boolean - validity status of the user account;
    private List<Quiz> quizzes;               //  list of all Quizzes created by the current User;

    public User() {
    }

    public User(String email, String username, String password, Gender gender, Role role, boolean status, List<Quiz> quizzes) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.status = status;
        this.quizzes = quizzes;
    }

    public User(String email, String username, String password, Gender gender, Role role, String picture, String description, String metadata, boolean status, List<Quiz> quizzes, List<QuizResult> results, int overallScore, List<Quiz> quizzesBlocked) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.picture = picture;
        this.description = description;
        this.metadata = metadata;
        this.status = status;
        this.quizzes = quizzes;
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

    public boolean isStatus() {
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
}
