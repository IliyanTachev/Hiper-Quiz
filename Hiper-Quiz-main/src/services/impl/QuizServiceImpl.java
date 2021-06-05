package services.impl;

import dao.QuizRepository;
import dao.Repository;
import model.Quiz;
import services.QuizResultService;
import services.QuizService;

public class QuizServiceImpl extends ServiceImpl<Long, Quiz> implements QuizService {
    public QuizServiceImpl(QuizRepository quizRepository) {
        super(quizRepository);
    }
}
