package dao;

import exception.EntityAlreadyExistsException;
import exception.EntityCreationException;
import exception.EntityNotFoundException;
import exception.EntityUpdateException;
import model.Identifiable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Repository<K, V extends Identifiable<K>> {
        List<V> findAll();
        Optional<V> findById(K id);
        V create(V entity) throws EntityAlreadyExistsException;
        V update(V entity) throws EntityNotFoundException, EntityUpdateException;
        V deleteById(K id) throws EntityNotFoundException, EntityUpdateException;
        long count();
        public int createBatch(Collection<V> entityCollection) throws EntityAlreadyExistsException, EntityCreationException;
        Repository<K, V> getRepository();
        void setKeyGenerator(KeyGenerator<K> keyGenerator);
}
