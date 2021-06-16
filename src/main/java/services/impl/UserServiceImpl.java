package services.impl;

import dao.UserRepository;
import dao.impl.UserRepositoryInMemoryImpl;
import exception.EntityNotFoundException;
import model.LoginUser;
import model.User;
import services.UserService;

import javax.persistence.NoResultException;
import java.util.Optional;

public class UserServiceImpl extends ServiceImpl<Long, User> implements UserService {
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public User login(LoginUser user) throws EntityNotFoundException {
        Optional<User> fetchedUser;
        try{
            fetchedUser = ((UserRepository)super.repository).findByUsernameAndPassword(user);
        } catch(NoResultException e) {
            throw new EntityNotFoundException("Invalid username or password. Please try again.");
        }

            UserRepository userRepository = null;
            if(super.repository instanceof UserRepository)
                userRepository = (UserRepository) super.repository;
            if(userRepository != null) userRepository.setLoggedUser(fetchedUser.get());
            return userRepository.getLoggedUser();
    }

    @Override
    public User logout() {
        ((UserRepository)super.repository).setLoggedUser(null);
        return ((UserRepository)super.repository).getLoggedUser();
    }

    @Override
    public User getLoggedUser() {
        return ((UserRepository)super.repository).getLoggedUser();
    }
}
