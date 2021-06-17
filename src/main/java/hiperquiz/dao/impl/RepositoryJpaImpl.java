package hiperquiz.dao.impl;

import hiperquiz.dao.KeyGenerator;
import hiperquiz.dao.Repository;
import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.exception.EntityCreationException;
import hiperquiz.exception.EntityNotFoundException;
import hiperquiz.exception.EntityUpdateException;
import hiperquiz.entities.Identifiable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class RepositoryJpaImpl<K, V extends Identifiable<K>> implements Repository<K, V> {
    private EntityManagerFactory emf;

//    @PersistenceContext
    private EntityManager em;

    public void init(){
        emf = Persistence.createEntityManagerFactory("hiperQuizPU");
        em = emf.createEntityManager();
    }

    @Override
    public List<V> findAll() {
        return null;
    }

    @Override
    public Optional<V> findById(K id) {
        return null;
    }

    @Override
    public V create(V entity) throws EntityAlreadyExistsException {
        return null;
    }

    @Override
    public V update(V entity) throws EntityNotFoundException, EntityUpdateException {
        return null;
    }

    @Override
    public V deleteById(K id) throws EntityNotFoundException, EntityUpdateException {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public int createBatch(Collection<V> entityCollection) throws EntityAlreadyExistsException, EntityCreationException {
        return 0;
    }

    @Override
    public Repository<K, V> getRepository() {
        return null;
    }

    @Override
    public void setKeyGenerator(KeyGenerator<K> keyGenerator) {

    }

    public EntityManager getEntityManager() {
        return em;
    }
}
