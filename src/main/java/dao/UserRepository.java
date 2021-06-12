package dao;
import exception.EntityAlreadyExistsException;
import exception.NoAuthorFoundException;
import model.LoginUser;
import model.Quiz;
import model.User;

import java.util.Optional;

public interface UserRepository extends Repository<Long, User>{
    Optional<User> findByUsernameAndPassword(LoginUser loginUser);
}
