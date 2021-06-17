package hiperquiz.dao.impl;

import hiperquiz.dao.KeyGenerator;
import hiperquiz.dao.QuestionRepository;
import hiperquiz.entities.Question;

public class QuestionRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, Question> implements QuestionRepository {
    public QuestionRepositoryInMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
