package br.com.blz.testjava.infrastructure;

import br.com.blz.testjava.controller.ProductController;
import br.com.blz.testjava.exception.ProductAreadyExistingException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ProductController.class)
public class CustomHandlerException {

    private Logger logger = LoggerFactory.getLogger(CustomHandlerException.class);


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> repositoryHandlerException(ProductNotFoundException ex) {

        logger.error(ex.getMessage(), ex);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ProductAreadyExistingException.class)
    public ResponseEntity<String> repositoryHandlerException(ProductAreadyExistingException ex) {

        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
