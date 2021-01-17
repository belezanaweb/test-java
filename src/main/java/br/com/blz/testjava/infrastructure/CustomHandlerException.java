package br.com.blz.testjava.infrastructure;

import br.com.blz.testjava.controller.ProductController;
import br.com.blz.testjava.controller.response.ErrorResponse;
import br.com.blz.testjava.exception.ProductAreadyExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(basePackageClasses = ProductController.class)
public class CustomHandlerException {

    private Logger logger = LoggerFactory.getLogger(CustomHandlerException.class);


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> repositoryHandlerException(ProductNotFoundException ex) {

        logger.error(ex.getMessage(), ex);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ProductAreadyExistException.class)
    public ResponseEntity<String> repositoryHandlerException(ProductAreadyExistException ex) {

        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationHandlerException(MethodArgumentNotValidException ex) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String errorMessage = MessageFormat.format(
            "{0}",
            fieldErrors
            .stream()
            .map(error ->  String.format("%s: %s", error.getField(), error.getDefaultMessage()))
            .collect(Collectors.joining(", "))
        );

        logger.error(errorMessage, ex);
        return new ResponseEntity<>(new ErrorResponse(errorMessage), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
