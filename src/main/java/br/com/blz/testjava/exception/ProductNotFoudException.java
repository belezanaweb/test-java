package br.com.blz.testjava.exception;

/**
 * @author Augusto Lemes
 * @since 16/06/2019
 *
 */
public class ProductNotFoudException extends BusinessException {

	private static final long serialVersionUID = -6348717994155017126L;
	
    public ProductNotFoudException(String message) {
        super(message);
    }
	

}
