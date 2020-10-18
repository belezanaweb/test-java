package br.com.blz.testjava.service;

public interface BaseService<T, ID> {

	void save(T entity);

	Iterable<T> findAll();
}
