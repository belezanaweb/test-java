package br.com.blz.testjava.services;

public interface ServiceCRUD<D, E> {
	
	public E create(D obj);

	public void update(Long id, D obj);

	public void destroy(Long id);

	public E search(Long id);
}
