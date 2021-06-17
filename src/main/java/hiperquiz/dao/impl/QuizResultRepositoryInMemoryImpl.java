package hiperquiz.dao.impl;

import hiperquiz.dao.KeyGenerator;
import hiperquiz.dao.QuizResultRepository;
import hiperquiz.entities.QuizResult;

public class QuizResultRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, QuizResult> implements QuizResultRepository {
    public QuizResultRepositoryInMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
