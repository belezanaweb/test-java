package br.com.blz.testjava.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ErrorDetails {
	@JsonSerialize(as = Date.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy hh:mm:ss")
	private Date timestamp;
	private String message;
	private String details;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}
