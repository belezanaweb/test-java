package br.com.blz.service;

import br.com.blz.exception.CrudException;

public interface AbstractService<T> {
	
	void create(T object) throws CrudException;
	void delete(Long id);
	T byId(Long id);
	void update(T object);

}
