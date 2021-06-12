package services.impl;

import dao.UserRepository;
import dao.impl.UserRepositoryInMemoryImpl;
import exception.EntityNotFoundException;
import model.LoginUser;
import model.User;
import services.UserService;

import java.util.Optional;

public class UserServiceImpl extends ServiceImpl<Long, User> implements UserService {
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public User login(LoginUser user) throws EntityNotFoundException {
        Optional<User> fetchedUser = ((UserRepositoryInMemoryImpl)super.repository).findByUsernameAndPassword(user);
        if(fetchedUser.isPresent()){
            UserRepositoryInMemoryImpl userRepositoryInMemory = null;
            if(super.repository instanceof UserRepositoryInMemoryImpl)
                userRepositoryInMemory = (UserRepositoryInMemoryImpl) super.repository;
            if(userRepositoryInMemory != null) userRepositoryInMemory.setLoggedUser(fetchedUser.get());
            return userRepositoryInMemory.getLoggedUser();
        } else throw new EntityNotFoundException("Invalid username or password. Please try again.");
    }

    @Override
    public User logout() {
        ((UserRepositoryInMemoryImpl)super.repository).setLoggedUser(null);
        return ((UserRepositoryInMemoryImpl)super.repository).getLoggedUser();
    }

    @Override
    public User getLoggedUser() {
        return ((UserRepositoryInMemoryImpl)super.repository).getLoggedUser();
    }
}
