package br.com.blz.testjava.service;

public class ProdutoException extends Exception {
	
	private static final long serialVersionUID = 4443226917548390623L;

	public ProdutoException (String errorMessage) {
        super(errorMessage);
    }

}
