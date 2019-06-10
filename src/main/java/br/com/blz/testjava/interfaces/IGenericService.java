package br.com.blz.testjava.interfaces;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IGenericService<E, K> {
    public void saveOrUpdate(E entity);
    public List<E> getAll();
	public E get(UUID key);
	public E get(E e);
    public void add(E entity);
	public E addAndReturn(E entity);
    public void update(E entity);
    public void remove(E entity);
	public Long count(Map<String, Object> map );
	public Long count();
}