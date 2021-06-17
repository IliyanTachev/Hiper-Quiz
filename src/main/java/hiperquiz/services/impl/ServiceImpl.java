package hiperquiz.services.impl;

import hiperquiz.dao.Repository;
import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.exception.EntityNotFoundException;
import hiperquiz.exception.EntityUpdateException;
import hiperquiz.entities.Identifiable;
import hiperquiz.services.Service;

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
    public V update(V entity) throws EntityNotFoundException, EntityUpdateException {
        return repository.update(entity);
    }

    @Override
    public V delete(K id) throws EntityNotFoundException, EntityUpdateException {
        return repository.deleteById(id);
    }

    @Override
    public List<V> getAll() {
        return repository.findAll();
    }

    @Override
    public V getById(K id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Product with ID='%d' not found", id)));
    }

    @Override
    public Repository<K, V> getRepository() {
        return repository.getRepository();
    }
}
