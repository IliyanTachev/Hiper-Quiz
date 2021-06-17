package hiperquiz.services.impl;

import hiperquiz.dao.UserRepository;
import hiperquiz.exception.EntityNotFoundException;
import hiperquiz.entities.LoginUser;
import hiperquiz.entities.User;
import hiperquiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Optional;

@Service
public class UserServiceImpl extends ServiceImpl<Long, User> implements UserService {
    @Autowired
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
