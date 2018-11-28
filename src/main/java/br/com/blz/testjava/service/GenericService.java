package br.com.blz.testjava.service;

import java.util.List;

public interface GenericService<T> {

	List<T> listAll();

	void save(T obj);

	void update(T obj);

	void remove(T obj);

	void removeById(long id);

	boolean validate(T obj);

	boolean validateInsert(T obj);
	
	T findById(long id);
}
