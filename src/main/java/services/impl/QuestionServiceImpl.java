package services.impl;

import dao.QuestionRepository;
import dao.Repository;
import model.Question;
import services.QuestionService;

public class QuestionServiceImpl extends ServiceImpl<Long, Question> implements QuestionService {
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        super(questionRepository);
    }
}
