package dao.impl;

import dao.UserRepository;
import exception.EntityAlreadyExistsException;
import exception.EntityCreationException;
import exception.EntityNotFoundException;
import exception.EntityUpdateException;
import model.LoginUser;
import model.User;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJPAImpl extends RepositoryJpaImpl<Long, User> implements UserRepository {
    private User loggedUser;
    @Override
    public User getLoggedUser() {
        return loggedUser;
    }
    @Override
    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public Optional<User> findByUsernameAndPassword(LoginUser loginUser) throws NoResultException {
        return Optional.ofNullable(getEntityManager().createQuery("select user from User as user where user.username='" + loginUser.getUsername() + "' and user.password='" + loginUser.getPassword() + "'", User.class).getSingleResult());
    }

    @Override
    public List<User> findAll() {
        return getEntityManager().createQuery("select user from User as user", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(getEntityManager().find(User.class, id));
    }

    @Override
    public User create(User user) {
        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.begin();
        getEntityManager().persist(user);
        transaction.commit();
        return user;
    }

    @Override
    public User update(User user) throws EntityNotFoundException, EntityUpdateException {
        Optional<User> old = findById(user.getId());
        if(old.isEmpty()){
            throw new EntityNotFoundException(String.format("User with ID='%s' does not exist.", user.getId()));
        }

        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.begin();

        try {
            getEntityManager().merge(user);
            transaction.commit();
            return user;
        } catch (IllegalArgumentException | PersistenceException e) {
            transaction.rollback();
            throw new EntityUpdateException("Error updating user:" + user, e);
        }
    }

    @Override
    public User deleteById(Long id) throws EntityNotFoundException, EntityUpdateException {
        Optional<User> userFound = findById(id);
        if(userFound.isEmpty()){
            throw new EntityNotFoundException(String.format("User with ID='%s' does not exist.", id));
        }

        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.begin();
        try {
            getEntityManager().remove(userFound);
            transaction.commit();
            return userFound.get();
        } catch (IllegalArgumentException | PersistenceException e) {
            transaction.rollback();
            throw new EntityUpdateException("Error deleting user:" + userFound, e);
        }
    }

    @Override
    public long count() {
        return (long) getEntityManager().createQuery("SELECT COUNT(user) FROM User as user").getSingleResult();
    }

    @Override
    public int createBatch(Collection<User> userList) throws EntityAlreadyExistsException, EntityCreationException {
        List<User> usersPersisted = new ArrayList<>();
        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.begin();
        try {
            for (User u : userList) {
                getEntityManager().persist(u);
                usersPersisted.add(u);
            }
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            throw new EntityCreationException("Error creating users batch ", e);
        }
        return usersPersisted.size();
    }
}
