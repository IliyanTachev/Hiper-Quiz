package hiperquiz.services;

import hiperquiz.exception.EntityNotFoundException;
import hiperquiz.entities.LoginUser;
import hiperquiz.entities.User;
public interface UserService extends Service<Long, User> {
    User login(LoginUser user) throws EntityNotFoundException;
    User logout();
    User getLoggedUser();
}
