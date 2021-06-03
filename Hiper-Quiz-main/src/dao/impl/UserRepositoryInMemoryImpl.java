package dao.impl;

import dao.KeyGenerator;
import dao.QuizRepository;
import dao.RepositoryInMemoryImpl;
import dao.UserRepository;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import exception.NoAuthorFoundException;
import model.Quiz;
import model.User;

public class UserRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, User> implements UserRepository {
    public UserRepositoryInMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
