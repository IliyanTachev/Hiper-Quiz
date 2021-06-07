package services;

import model.Quiz;

import java.util.Optional;

public interface QuizService extends Service<Long, Quiz>{
    Optional<Quiz> getById(Long id);
}
