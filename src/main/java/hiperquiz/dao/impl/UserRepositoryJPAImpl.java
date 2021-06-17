package hiperquiz.dao.impl;

import hiperquiz.dao.UserRepository;
import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.exception.EntityCreationException;
import hiperquiz.exception.EntityNotFoundException;
import hiperquiz.exception.EntityUpdateException;
import hiperquiz.entities.LoginUser;
import hiperquiz.entities.Player;
import hiperquiz.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryJPAImpl extends RepositoryJpaImpl<Long, User> implements UserRepository {
    private User loggedUser;

    @PersistenceContext
    private EntityManager em;


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
        return Optional.ofNullable(em.createQuery("select user from User as user where user.username='" + loginUser.getUsername() + "' and user.password='" + loginUser.getPassword() + "'", User.class).getSingleResult());
    } // try catch

    @Override
    public List<User> findAll() {
        return em.createQuery("select user from User as user", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public User create(User user) {
//        Player player = new Player(user);
        em.persist(user); // set default Role as Player
        return user;
    }

    @Override
    public User update(User user) throws EntityNotFoundException, EntityUpdateException {
        Optional<User> old = findById(user.getId());

        if(old.isEmpty())
            throw new EntityNotFoundException(String.format("User with ID='%s' does not exist.", user.getId()));

        try {
            em.merge(user);
            return user;
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new EntityUpdateException("Error updating user:" + user, e);
        }
    }

    @Override
    public User deleteById(Long id) throws EntityNotFoundException, EntityUpdateException {
        Optional<User> userFound = findById(id);
        if(userFound.isEmpty())
            throw new EntityNotFoundException(String.format("User with ID='%s' does not exist.", id));

        try {
            em.remove(userFound);
            return userFound.get();
        } catch (IllegalArgumentException | PersistenceException e) {
            throw new EntityUpdateException("Error deleting user:" + userFound, e);
        }
    }

    @Override
    public long count() {
        return (long) em.createQuery("SELECT COUNT(user) FROM User as user").getSingleResult();
    }

    @Override
    public int createBatch(Collection<User> userList) throws EntityAlreadyExistsException, EntityCreationException {
        List<User> usersPersisted = new ArrayList<>();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            for (User u : userList) {
                em.persist(u);
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
