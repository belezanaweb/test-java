package br.com.blz.testjava.error;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = ANY)
public class ErrorResponse {

	private final int statusCode;
	private final List<AppError> errors;

	private ErrorResponse(int statusCode, List<AppError> errors) {
		this.statusCode = statusCode;
		this.errors = errors;
	}

	static ErrorResponse of(HttpStatus status, List<AppError> errors) {
		return new ErrorResponse(status.value(), errors);
	}

	static ErrorResponse of(HttpStatus status, AppError error) {
		return of(status, Collections.singletonList(error));
	}

	public int getStatusCode() {
		return statusCode;
	}

	public List<AppError> getErrors() {
		return errors;
	}

	@JsonAutoDetect(fieldVisibility = ANY)
	static class AppError {
		private final String code;
		private final String message;

		public AppError(String code, String message) {
			super();
			this.code = code;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	}

}
