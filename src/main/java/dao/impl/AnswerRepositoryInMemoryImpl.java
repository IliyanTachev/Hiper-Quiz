package dao.impl;

import dao.AnswerRepository;
import dao.KeyGenerator;
import dao.Repository;
import dao.RepositoryInMemoryImpl;
import model.Answer;

public class AnswerRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, Answer> implements AnswerRepository {
    public AnswerRepositoryInMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
