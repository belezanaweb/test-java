package br.com.blz.testjava.repository;

import java.util.List;

public interface Repository<T, K> {

	List<T> findAll();
	
	T findById(K key);
	
	void create(T entity);
	
	void update(T entity);
	
	void delete(T entity);
	
}
