package br.com.blz.testjava.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse<T> {

	@JsonProperty
	private Integer status;

	@JsonProperty
	private String message;

	@JsonProperty
	private T response;

	public ProductResponse(Integer status, T response) {
		this(status, null, response);
	}

	public ProductResponse(Integer httpStatus, String message) {
		this(httpStatus, message, null);
	}

	public ProductResponse(Integer status, String message, T response) {
		this.status = status;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}