package br.com.blz.testjava.interceptors;

import br.com.blz.testjava.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> skuExistsException(
            BusinessException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> generalException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
