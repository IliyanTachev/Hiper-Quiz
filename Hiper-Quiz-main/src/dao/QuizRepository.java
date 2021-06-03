package dao;

import model.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends Repository<Long, Quiz>{
    Optional<Quiz> findByTitle(String title);
    Optional<Quiz> findByDescription(String description);
    Optional<Quiz> findByTags(String tags);
    List<Quiz> findByExpectedDuration(int expectedDuration);
}
