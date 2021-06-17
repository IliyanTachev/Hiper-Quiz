package dao.impl;

import hiperquiz.dao.KeyGenerator;
import hiperquiz.dao.QuizRepository;
import hiperquiz.dao.impl.LongKeyGenerator;
import hiperquiz.dao.impl.QuizRepositoryInMemoryImpl;
import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.entities.Quiz;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class QuizRepositoryInMemoryImplTest {
    private static final List<Quiz> SAMPLE_QUIZZES = List.of(
            new Quiz("Mathematica", "Math quiz 1", 2, "picture example 1", "math, quiz"),
            new Quiz("Mathematica", "Math quiz 2", 4, "picture example 1.5", "math, quiz"),
            new Quiz("History","History quiz", 5, "picture example 2", "history, quiz"),
            new Quiz("NETFLIX", "Quiz for shows on Netflix", 10, "picture example 3", "shows, netflix, chill"),
            new Quiz("Anime", "Quiz for anime characters", 3, "picture example 4",  "yugioh,wolfsrain, anime")
    );

    private static KeyGenerator<Long> keyGenerator;
    private static QuizRepository repo;

    @BeforeAll
    public static void setUp(){
        keyGenerator = new LongKeyGenerator(0);
        repo = new QuizRepositoryInMemoryImpl(keyGenerator);
        fillInEntities();
    }

    @Test
    @DisplayName("Find Quiz by Title")
    void findByTitle() {
        Set<Quiz> quizFound = repo.findByTitle(SAMPLE_QUIZZES.get(0).getTitle());
        assertNotNull(quizFound, "Quizzes result from searching by Title is null");
        assertEquals(quizFound.size(), 2);
    }

    @Test
    void findByDescription() {
    }

    @Test
    void findByTags() {
    }

    @Test
    void findByExpectedDuration() {
    }

    @Test
    void findByAllCriterias() {
    }

    private static void fillInEntities(){
        SAMPLE_QUIZZES.forEach(q -> {
            try {
                repo.create(q);
            } catch (EntityAlreadyExistsException e) {
//                log.error("Error adding products to repository", e);
            }
        });
    }
}