package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="quizzes")
public class Quiz extends AbstractEntity<Long, Quiz> {
    @NotNull
    @Size(min=2, max=80)
    private String title;            // string 2 to 80 characters long;
    @ManyToOne
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private User author;             // the User that created the Quiz;
    @Size(min=20, max=250)
    private String description;      // string 20 - 250 characters long, supporting Markdown syntax;
    @OneToMany(mappedBy = "quiz")
    private List<Question> questions = new ArrayList<>(); // list of Question entities (containing the answers with their scores too);
    @NotNull
    private int expectedDuration; // integer number in minutes;
    private String picture;          // (optional) - best representing the Quiz, valid URL to a picture, if missing a placeholder picture should be used;
    @NotNull
    private String tags;             // string including comma separated tags, allowing to find the Quizes by quick search;
    @ManyToOne
    @JoinColumn(name="blocked_by_admin_id", referencedColumnName = "id")
    private Administrator blocked_from_admin;

    public Quiz() {
    }

    public Quiz(String title, String description, int expectedDuration, String picture, String tags) {
        this.title = title;
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.picture = picture;
        this.tags = tags;
    }

    public Quiz(String title, User author, String description, List<Question> questions, int expectedDuration, String picture, String tags) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.questions = questions;
        this.expectedDuration = expectedDuration;
        this.picture = picture;
        this.tags = tags;
    }

    public Quiz(String title, User author, String description, int expectedDuration, String tags) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(int expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Question addQuestion(Question question){
        this.questions.add(question);
        return question;
    }

    public int getQuestionsSize(){
        return questions.size();
    }

    public Administrator getBlocked_from_admin() {
        return blocked_from_admin;
    }

    public void setBlocked_from_admin(Administrator blocked_from_admin) {
        this.blocked_from_admin = blocked_from_admin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("id=").append(getId());
        sb.append('}');
        return sb.toString();
    }
}
