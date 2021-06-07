package services.impl;

import dao.QuizRepository;
import dao.Repository;
import model.Quiz;
import services.QuizResultService;
import services.QuizService;

import java.util.Optional;

public class QuizServiceImpl extends ServiceImpl<Long, Quiz> implements QuizService {
    public QuizServiceImpl(QuizRepository quizRepository) {
        super(quizRepository);
    }

    @Override
    public Optional<Quiz> getById(Long id) {
        return repository.findById(id);
    }
}
