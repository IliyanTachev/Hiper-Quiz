package services;

import exception.EntityNotFoundException;
import model.LoginUser;
import model.User;
public interface UserService extends Service<Long, User> {
    User login(LoginUser user) throws EntityNotFoundException;
    User logout();
    User getLoggedUser();
}
