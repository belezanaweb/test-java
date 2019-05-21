package br.com.blz.testjava.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

/**
 * Generic Service para ser utilizado pelas entidades
 * @param <T>
 * @param <ID>
 */

public interface GenericService<T, ID> {
    void saveOrUpdate(T entity);
    List<T> getAll();
    Page<T> getAllPaginated(int page, int count, Sort.Direction direction, String sortProperty);
    T get(ID id);
    T add(T entity);
    T update(T entity);
    T addOrUpdate(T entity);
    void updateEntities(Set<T> entitys);
    void remove(T entity);
    void removeById(ID id);
    void removeEntities(Set<T> entitys);
    void removeEntitiesById(List<ID> entityIds);
}


