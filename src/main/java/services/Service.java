package services;

import dao.Repository;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import exception.EntityUpdateException;
import model.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Service<K, V extends Identifiable<K>> {
    V create(V entity) throws EntityAlreadyExistsException;
    Optional<V> read(K id);
    V update(V entity) throws EntityNotFoundException, EntityUpdateException;
    V delete(K id) throws EntityNotFoundException, EntityUpdateException;
    List<V> getAll();
    Optional<V> getById(K id);
    Repository<K, V> getRepository();
}
