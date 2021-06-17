package hiperquiz.dao;

import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.exception.EntityCreationException;
import hiperquiz.exception.EntityNotFoundException;
import hiperquiz.exception.EntityUpdateException;
import hiperquiz.entities.Identifiable;

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
