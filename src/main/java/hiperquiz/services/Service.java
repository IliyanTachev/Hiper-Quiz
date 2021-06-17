package hiperquiz.services;

import hiperquiz.dao.Repository;
import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.exception.EntityNotFoundException;
import hiperquiz.exception.EntityUpdateException;
import hiperquiz.entities.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Service<K, V extends Identifiable<K>> {
    V create(V entity) throws EntityAlreadyExistsException;
    Optional<V> read(K id);
    V update(V entity) throws EntityNotFoundException, EntityUpdateException;
    V delete(K id) throws EntityNotFoundException, EntityUpdateException;
    List<V> getAll();
    V getById(K id);
    Repository<K, V> getRepository();
}
