package br.com.blz.testjava.exepctions;

/**
 *
 * @author andrey
 * @since 2019-11-10
 */
public class BlzException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public static BlzException newInstance(String message, Object... args) {
		return new BlzException(String.format(message, args));
	}
	
	public BlzException(String message) {
		super(message);
	}

}