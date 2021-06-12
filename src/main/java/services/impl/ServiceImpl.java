package services.impl;

import dao.Repository;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import model.Identifiable;
import services.Service;

import java.util.List;
import java.util.Optional;

public class ServiceImpl<K, V extends Identifiable<K>> implements Service<K, V> {
    Repository<K, V> repository;

    public ServiceImpl(Repository<K, V> repository) {
        this.repository = repository;
    }

    @Override
    public V create(V entity) throws EntityAlreadyExistsException {
        return repository.create(entity);
    }

    @Override
    public Optional<V> read(K id) {
        return repository.findById(id);
    }

    @Override
    public V update(V entity) throws EntityNotFoundException {
        return repository.update(entity);
    }

    @Override
    public V delete(K id) throws EntityNotFoundException {
        return repository.deleteById(id);
    }

    @Override
    public List<V> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<V> getById(K id) {
        return repository.findById(id);
    }

    @Override
    public Repository<K, V> getRepo() {
        return repository.getRepository();
    }
}
