package dao.impl;

import dao.KeyGenerator;
import dao.RepositoryInMemoryImpl;
import dao.UserRepository;
import model.LoginUser;
import model.User;

import java.util.Optional;

public class UserRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, User> implements UserRepository {
    public UserRepositoryInMemoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }

    private User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public Optional<User> findByUsernameAndPassword(LoginUser user) {
        return findAll().stream().filter(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())).findFirst();
    }
}
