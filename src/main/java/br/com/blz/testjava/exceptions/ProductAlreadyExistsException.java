package br.com.blz.testjava.exceptions;

public class ProductAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -5790655788815518894L;

	public ProductAlreadyExistsException(){
		super("Produto já existe");
	}
	
	
	
}
