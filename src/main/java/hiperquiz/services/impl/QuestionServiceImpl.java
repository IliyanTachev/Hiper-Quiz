package hiperquiz.services.impl;

import hiperquiz.dao.QuestionRepository;
import hiperquiz.entities.Question;
import hiperquiz.services.QuestionService;

public class QuestionServiceImpl extends ServiceImpl<Long, Question> implements QuestionService {
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        super(questionRepository);
    }
}
