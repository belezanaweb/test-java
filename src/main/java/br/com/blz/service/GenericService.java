package br.com.blz.service;

public interface GenericService<T> {

	void save(T obj) throws Exception;

	void update(T obj) throws Exception;

	void delete(T obj);

	boolean validate(T obj);

	boolean productExist(T obj);
	

}