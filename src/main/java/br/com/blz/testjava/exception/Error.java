package br.com.blz.testjava.exception;

import java.time.LocalDate;

public class Error {

	private LocalDate timestamp;
	private String message;
	private String details;



	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Error(LocalDate localDate, String message, String details) {
		super();
		this.setTimestamp(localDate);
		this.message = message;
		this.details = details;
	}


	public LocalDate getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}

}
