package br.com.blz.testjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@NoRepositoryBean
public abstract class InMemoryRepository<T, ID> implements CrudRepository<T, ID> {

    private final Map<ID, T> entities;

    protected InMemoryRepository() {
        entities = new HashMap<>();
    }

    abstract <S extends T> ID getEntityId(S entity);

    @Override
    public <S extends T> S save(S entity) {
        entities.put(getEntityId(entity), entity);
        return entity;
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        final List<S> result = new ArrayList<>();
        entities.forEach(entity -> result.add(save(entity)));
        return result;
    }

    @Override
    public Collection<T> findAll() {
        return entities.values();
    }

    @Override
    public long count() {
        return entities.keySet().size();
    }

    @Override
    public void delete(T entity) {
        deleteById(getEntityId(entity));
    }

    @Override
    public void deleteAll(Iterable<? extends T> entitiesToDelete) {
        entitiesToDelete.forEach(entity -> entities.remove(getEntityId(entity)));
    }

    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public void deleteById(ID id) {
        entities.remove(id);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        final List<T> result = new ArrayList<>();
        ids.forEach(id -> findById(id).ifPresent(result::add));
        return result;
    }

    @Override
    public boolean existsById(ID id) {
        return entities.containsKey(id);
    }

    public T findOne(ID id) {
        return entities.get(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(findOne(id));
    }
}
