package br.com.blz.testjava.exception;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.servlet.resource.HttpResource;

@RestControllerAdvice
public class ExceptionControllerAdivice {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
		return new ResponseEntity<>(ex.getApiErrorResponse(),
				HttpStatus.valueOf(Integer.parseInt(ex.getApiErrorResponse().getCode())));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
		ApiErrorResponse apiErrorResponse = ApiErrorResponse.of(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage(), ex.getLocalizedMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiErrorResponse> handleInternalError(HttpRequest req, HttpResource res, Exception ex) {
		ApiErrorResponse apiErrorResponse = ApiErrorResponse.of(
				String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage(), ex.getLocalizedMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiErrorResponse> handleBadRequest(HttpRequest req, HttpResource res, BadRequest ex) {
		ApiErrorResponse apiErrorResponse = ApiErrorResponse.of(String.valueOf(HttpStatus.BAD_REQUEST.value()),
				ex.getResponseBodyAsString(), ex.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

}