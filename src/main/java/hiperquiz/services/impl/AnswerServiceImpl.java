package hiperquiz.services.impl;

import hiperquiz.dao.AnswerRepository;
import hiperquiz.entities.Answer;
import hiperquiz.services.AnswerService;

public class AnswerServiceImpl extends ServiceImpl<Long, Answer> implements AnswerService {
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        super(answerRepository);
    }
}
