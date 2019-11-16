package br.com.blz.testjava.exception.handler;

import br.com.blz.testjava.controller.response.ExceptionResponse;
import br.com.blz.testjava.exception.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Autowired
    private ObjectMapper mapper;
    
    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request)
        throws JsonProcessingException {
        
        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
        responseStatus.value();
        
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),
            responseStatus.code().value());
        
        String bodyOfResponse = mapper.writeValueAsString(exceptionResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
            responseStatus.code(), request);
    }
}
