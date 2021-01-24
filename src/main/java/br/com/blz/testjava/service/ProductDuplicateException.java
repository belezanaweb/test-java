package br.com.blz.testjava.service;

public class ProductDuplicateException extends RuntimeException {

	public ProductDuplicateException(final Long sku ) {
		super("A SKU " + sku  + " já possui cadastro no sistema");
	} 

}
