package br.com.blz.testjava.exception;

import java.util.Locale;
import java.util.ResourceBundle;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = -2743573231601700804L;

	private String message;
	
	public ApiException(String chave) {

		ResourceBundle mb = ResourceBundle.getBundle("messages-error", new Locale("pt", "BR"));
		this.message = mb.getString(chave);
	}

	public String getMessage(){
		return this.message;
	}

}
