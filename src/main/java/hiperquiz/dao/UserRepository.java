package hiperquiz.dao;
import hiperquiz.entities.LoginUser;
import hiperquiz.entities.User;

import java.util.Optional;

public interface UserRepository extends Repository<Long, User>{
    Optional<User> findByUsernameAndPassword(LoginUser loginUser);
    User getLoggedUser();
    void setLoggedUser(User loggedUser);
}
