package br.com.blz.exception;

public class BusinessException extends Exception {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 8120378308853401514L;

	public BusinessException(String message) {
        super(message);
    }
}