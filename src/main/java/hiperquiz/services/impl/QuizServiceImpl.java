package hiperquiz.services.impl;

import hiperquiz.dao.QuizRepository;
import hiperquiz.entities.Quiz;
import hiperquiz.services.QuizService;

import java.util.Optional;

public class QuizServiceImpl extends ServiceImpl<Long, Quiz> implements QuizService {
    public QuizServiceImpl(QuizRepository quizRepository) {
        super(quizRepository);
    }

    @Override
    public Quiz getById(Long id) {
        return super.getById(id);
    }
}
