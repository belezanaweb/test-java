package br.com.blz.service;

public interface CrudService <T, ID>{
	
	T getById(ID id);
	T save(T entity);
	T edit(T entity);
	void deleteById (ID id);

}
