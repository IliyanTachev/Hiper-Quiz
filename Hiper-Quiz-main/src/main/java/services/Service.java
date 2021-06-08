package services;

import dao.Repository;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import model.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Service<K, V extends Identifiable<K>> {
    V create(V entity) throws EntityAlreadyExistsException;
    Optional<V> read(K id);
    V update(V entity) throws EntityNotFoundException;
    V delete(K id) throws EntityNotFoundException;
    List<V> getAll();
    Optional<V> getById(K id);
    Repository<K, V> getRepo();
}
