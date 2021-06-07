package model;

import java.util.List;

public class Player extends User{
    private List<QuizResult> results = null;  // for PLAYERS only

    public Player() {
    }

    public Player(List<QuizResult> results) {
        this.results = results;
    }

    public List<QuizResult> getResults() {
        return results;
    }

    public void setResults(List<QuizResult> results) {
        this.results = results;
    }

    public int getOverallScore() {
        int totalScore = 0;
        for(QuizResult quizResult : results){
            totalScore += quizResult.getScore();
        }
        return totalScore;
    }

    public Rank getRank(){
        if(getOverallScore() <= 125) return Rank.JUNIOR;
        else if(getOverallScore() <= 250) return Rank.MEDIUM;
        return Rank.SENIOR;
    }

    public QuizResult addResult(QuizResult quizResult){
        results.add(quizResult);
        return quizResult;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("results=").append(results);
        sb.append(", email='").append(getEmail()).append('\'');
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
