package br.com.blz.testjava.exception;

public class ProdutoNegocioException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7264963899718861812L;

	public ProdutoNegocioException(String errorMessage) {
        super(errorMessage);
    }
}