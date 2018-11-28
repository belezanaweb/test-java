package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.blz.testjava.dto.ApiErrorDTO;

@ControllerAdvice
public class RestExceptionHandler /*extends ResponseEntityExceptionHandler*/ {

	   @ExceptionHandler(Exception.class)
	   protected ResponseEntity<ApiErrorDTO> handle(Exception ex) {
	       
		   ApiErrorDTO dto = new ApiErrorDTO();
		   dto.setErrorMessage(ex.getMessage());
		   
		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
	   }

	   @ExceptionHandler(ApiException.class)
	   protected ResponseEntity<ApiErrorDTO> handle(ApiException ex) {
	       
		   ApiErrorDTO dto = new ApiErrorDTO();
		   dto.setErrorMessage(ex.getMessage());
		   
		   return ResponseEntity.status(HttpStatus.GONE).body(dto);
	   }
	   
	   @ExceptionHandler(InvalidDataException.class)
	   protected ResponseEntity<Object> handle(InvalidDataException ex) {
		   
		   ApiErrorDTO dto = new ApiErrorDTO();
		   dto.setErrorMessage("Dados inválidos");
		   dto.setInvalidData(ex.getMessages());
		   
		   return ResponseEntity.badRequest().body(dto);
	   }
}
