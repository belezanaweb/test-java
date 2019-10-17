package br.com.blz.testjava.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> erros = Arrays.asList(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Error> errors = createErrorsList(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		String userMessage = "sku not found";
		String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> erros = Arrays.asList(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ ProductSkuAlreadyExistsException.class })
	public ResponseEntity<Object> handleProductSkuAlreadyExistsException(ProductSkuAlreadyExistsException ex, WebRequest request) {
		String userMessage = messageSource.getMessage("resource.sku-already-exists", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> erros = Arrays.asList(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler({ ProductNotExistsException.class })
	public ResponseEntity<Object> handleProductNotExistsException(ProductNotExistsException ex, WebRequest request) {
		String userMessage = messageSource.getMessage("resource.sku-not-exists", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> erros = Arrays.asList(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ ProductBadRequestException.class })
	public ResponseEntity<Object> handleProductBadRequestException(ProductBadRequestException ex, WebRequest request) {
		String userMessage = messageSource.getMessage("resource.update-bad-request", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> erros = Arrays.asList(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
 
	@ExceptionHandler({ DataIntegrityViolationException.class } )
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String userMessage = messageSource.getMessage("resource.operation-not-allows", null, LocaleContextHolder.getLocale());
		String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> erros = Arrays.asList(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Error> createErrorsList(BindingResult bindingResult) {
		List<Error> error = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String developerMessage = fieldError.toString();
			error.add(new Error(userMessage, developerMessage));
		}
			
		return error;
	}
	
	public static class Error {
		
		private String userMessage;
		private String developerMessage;
		
		public Error(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.userMessage = mensagemUsuario;
			this.developerMessage = mensagemDesenvolvedor;
		}

		public String getUserMessage() {
			return userMessage;
		}

		public void setUserMessage(String userMessage) {
			this.userMessage = userMessage;
		}

		public String getDeveloperMessage() {
			return developerMessage;
		}

		public void setDeveloperMessage(String developerMessage) {
			this.developerMessage = developerMessage;
		}
 
	}
}
