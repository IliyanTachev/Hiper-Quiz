package model;

import java.util.List;

public class Player extends User{
    private List<QuizResult> results = null;  // for PLAYERS only
    private int overallScore = 0;             // for PLAYERS only

    public Player() {
    }

    public Player(List<QuizResult> results, int overallScore) {
        this.results = results;
        this.overallScore = overallScore;
    }

    public List<QuizResult> getResults() {
        return results;
    }

    public void setResults(List<QuizResult> results) {
        this.results = results;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("results=").append(results);
        sb.append(", overallScore=").append(overallScore);
        sb.append(", email='").append(getEmail()).append('\'');
        sb.append(", username='").append(getUsername()).append('\'');
        sb.append(", password='").append(getPassword()).append('\'');
        sb.append(", gender=").append(getGender());
        sb.append(", role=").append(getRole());
        sb.append(", picture='").append(getPicture()).append('\'');
        sb.append(", description='").append(getDescription()).append('\'');
        sb.append(", metadata='").append(getMetadata()).append('\'');
        sb.append(", status=").append(isStatus());
        sb.append(", quizzes=").append(getQuizzes());
        sb.append('}');
        return sb.toString();
    }
}
