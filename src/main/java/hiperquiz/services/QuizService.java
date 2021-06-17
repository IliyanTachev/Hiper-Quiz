package hiperquiz.services;

import hiperquiz.entities.Quiz;

import java.util.Optional;

public interface QuizService extends Service<Long, Quiz>{
    Quiz getById(Long id);
}
