package br.com.blz.testjava.exception;

public class ProdutoNaoEncontradoException extends NegocioException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(Long sku) {
		super(String.format("Não existe um cadastro de produto com sku %d", sku));
	}
}
