package br.com.blz.testjava.service;

public interface BaseService<T, ID> {
	
	void save(T entity);
	
	T update(ID id, T entity);
	
	T findById(ID id);
	
	Iterable<T> findAll();
	
	void deleteById(ID id);

}