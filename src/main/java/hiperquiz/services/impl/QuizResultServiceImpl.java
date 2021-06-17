package hiperquiz.services.impl;

import hiperquiz.dao.QuizResultRepository;
import hiperquiz.entities.QuizResult;
import hiperquiz.services.QuizResultService;

public class QuizResultServiceImpl extends ServiceImpl<Long, QuizResult> implements QuizResultService {
    public QuizResultServiceImpl(QuizResultRepository quizResultRepository) {
        super(quizResultRepository);
    }
}
