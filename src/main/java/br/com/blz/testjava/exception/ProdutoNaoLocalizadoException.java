package br.com.blz.testjava.exception;

import br.com.blz.testjava.util.Util;

public class ProdutoNaoLocalizadoException extends RuntimeException {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProdutoNaoLocalizadoException(Integer sku) {
        super(Util.PRODUTO_NAO_ENCONTRADO + sku);
    }
}