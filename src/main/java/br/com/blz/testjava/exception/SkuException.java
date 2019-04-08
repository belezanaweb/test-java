package br.com.blz.testjava.exception;
public class SkuException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3988149473167810412L;

	public SkuException(String message) {
		super(message);
	}

	public SkuException(String message, Throwable source) {
		super(message, source);
	}

}