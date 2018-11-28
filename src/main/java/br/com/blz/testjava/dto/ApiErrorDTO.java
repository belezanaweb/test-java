package br.com.blz.testjava.dto;

import java.io.Serializable;
import java.util.List;

public class ApiErrorDTO implements Serializable {

	private static final long serialVersionUID = 4549405267191806055L;
	
	private String errorMessage;
	private List<String> invalidData;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<String> getInvalidData() {
		return invalidData;
	}

	public void setInvalidData(List<String> invalidData) {
		this.invalidData = invalidData;
	}

}
