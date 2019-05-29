package br.com.blz.testjava.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BusinessException(final String message) {
		super(message);
	}

	private BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construct an Exception with message.
	 *
	 * @param message
	 *            The description of the exception.
	 * @return An instance of the Exception.
	 */
	public static BusinessException getInstance(final String message) {
		return new BusinessException(message);
	}

	/**
	 * Construct an Exception with message and cause.
	 *
	 * @param message
	 *            The description of the exception.
	 * @param cause
	 *            The cause of the exception.
	 * @return An instance of the Exception.
	 */
	public static BusinessException getInstance(final String message, final Throwable cause) {
		return new BusinessException(message, cause);
	}

}
