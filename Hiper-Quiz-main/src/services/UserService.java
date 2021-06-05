package services;

import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user) throws EntityAlreadyExistsException;
    List<User> getAllUsers();
    User deleteUser(long id) throws EntityNotFoundException;
    User updateUser(User user) throws EntityNotFoundException;
    Optional<User> getUserById(Long id);
}
