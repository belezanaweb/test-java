package br.com.blz.testjava.base.config;

import br.com.blz.testjava.base.exceptions.DocumentAlreadyExistsException;
import br.com.blz.testjava.base.exceptions.DocumentNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class HandlerExceptionController {
    //TODO tratar erros

    @ExceptionHandler(value = {DocumentNotExistsException.class, DocumentAlreadyExistsException.class})
    public ResponseEntity<ExceptionResponse> handlerNotExistsException(RuntimeException ex){

        return new ResponseEntity<>(ExceptionResponse.builder()
            .message(ex.getMessage())
            .error(sanitizeError(HttpStatus.BAD_REQUEST.toString()))
            .status(HttpStatus.BAD_REQUEST.value())
            .build(),
            HttpStatus.BAD_REQUEST);

    }

    private String sanitizeError(String value){
        return value.replaceAll("[0-9]", "")
            .replace("_", " ").trim();
    }



}
