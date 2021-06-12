package services.impl;

import dao.AnswerRepository;
import dao.Repository;
import model.Answer;
import services.AnswerService;

public class AnswerServiceImpl extends ServiceImpl<Long, Answer> implements AnswerService {
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        super(answerRepository);
    }
}
