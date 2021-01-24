package br.com.blz.testjava.service;

public class ProductNotFound extends RuntimeException {

	public ProductNotFound(final Long sku ) {
		super("A SKU " + sku  + " já possui cadastro no sistema");
	} 

}
