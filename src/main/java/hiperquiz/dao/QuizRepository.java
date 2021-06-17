package hiperquiz.dao;

import hiperquiz.entities.Quiz;

import java.util.Set;

public interface QuizRepository extends Repository<Long, Quiz>{
    Set<Quiz> findByTitle(String title);
    Set<Quiz> findByDescription(String description);
    Set<Quiz> findByTags(String tags);
    Set<Quiz> findByExpectedDuration(int expectedDuration);
    Set<Quiz> findByAllCriterias(String title, String description, String tags, int expectedDuration);
}
