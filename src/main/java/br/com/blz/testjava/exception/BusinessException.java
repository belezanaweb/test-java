package br.com.blz.testjava.exception;

/**
 * @author Augusto Lemes
 * @since 16/06/2019
 *
 */
public abstract class BusinessException extends Exception{

	private static final long serialVersionUID = -7291796666933510339L;
	
    public BusinessException(String message) {
        super(message);
    }
	

}
