package hiperquiz.dao.impl;

import hiperquiz.dao.AnswerRepository;
import hiperquiz.dao.KeyGenerator;
import hiperquiz.entities.Answer;

public class AnswerRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, Answer> implements AnswerRepository {
    public AnswerRepositoryInMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
