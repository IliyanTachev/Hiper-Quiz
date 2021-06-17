package hiperquiz.dao.impl;

import hiperquiz.dao.KeyGenerator;
import hiperquiz.dao.UserRepository;
import hiperquiz.entities.LoginUser;
import hiperquiz.entities.User;

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
