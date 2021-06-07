package dao.impl;

import dao.KeyGenerator;
import dao.QuestionRepository;
import dao.RepositoryInMemoryImpl;
import model.Question;

public class QuestionRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, Question> implements QuestionRepository {
    public QuestionRepositoryInMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
