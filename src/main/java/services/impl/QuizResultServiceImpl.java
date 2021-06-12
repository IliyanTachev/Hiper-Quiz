package services.impl;

import dao.QuizResultRepository;
import dao.Repository;
import model.QuizResult;
import services.QuizResultService;

public class QuizResultServiceImpl extends ServiceImpl<Long, QuizResult> implements QuizResultService {
    public QuizResultServiceImpl(QuizResultRepository quizResultRepository) {
        super(quizResultRepository);
    }
}
