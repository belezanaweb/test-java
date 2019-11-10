package br.com.blz.testjava.util;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Classe wrapper para encapsular as respostas da api.
 * @author andrey
 * @since 2019-11-10
 */
public class ResponseUtil<T> {
	
	@JsonProperty
	private String message;

	@JsonProperty
	private T response;

	public ResponseUtil(String message) {
		this(message,null);
	}
	
	public ResponseUtil(String message, T response) {
		this.message = message;
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResponse() {
		return this.response;
	}

	public void setResponse(T response) {
		this.response = response;
	}
}