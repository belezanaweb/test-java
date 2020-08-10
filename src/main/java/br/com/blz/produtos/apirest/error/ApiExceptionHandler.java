package br.com.blz.produtos.apirest.error;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	
//	@ExceptionHandler(value = {Exception.class})
//	public ResponseEntity<Object> handleApiRequestException(Exception exception){
//		
//		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//	
//		
//		ApiException apiException =new ApiException(
//				"Erro Desconhecido: "+exception.getMessage(),
//				httpStatus.value(),
//				httpStatus, 
//				ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")),
//				exception.getCause()
//				);
//		
//		return new ResponseEntity<Object>(apiException, httpStatus);
//		
//	}
	
	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException exception){
		
		HttpStatus httpStatus = exception.getHttpStatus();
		
		ApiException apiException =new ApiException(
				exception.getMessage(),
				httpStatus.value(),
				httpStatus, 
				ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		
		return new ResponseEntity<Object>(apiException, httpStatus);
		
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleApiRequestException(MethodArgumentNotValidException exception){
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
		
		ApiException apiException =new ApiException(
				errors.get(0),
				httpStatus.value(),
				httpStatus, 
				ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		
		return new ResponseEntity<Object>(apiException, httpStatus);
		
	}
}
