package br.com.blz.testjava.exception;

public abstract class BaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4450457013817936515L;

	BaseException(String message){
		super(message);
	}
}
