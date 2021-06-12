package dao.impl;

import dao.AnswerRepository;
import dao.KeyGenerator;
import dao.QuizResultRepository;
import dao.RepositoryInMemoryImpl;
import model.QuizResult;

public class QuizResultRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, QuizResult> implements QuizResultRepository {
    public QuizResultRepositoryInMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
