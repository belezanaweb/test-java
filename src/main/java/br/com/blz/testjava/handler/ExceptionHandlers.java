package br.com.blz.testjava.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.dao.entity.Product;
import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.exception.ProductSKUMismatchException;
@ControllerAdvice
public class ExceptionHandlers extends BaseExceptionHandler {
	 static Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);
    public ExceptionHandlers() {
        super(logger);
    }
    
    private static final String PRODUCT_NOT_FOUND = "Product not found";

    
    @ExceptionHandler({ ProductNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(
      Exception ex, WebRequest request) {
    	logger.info(ex.getClass().getName());
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST.name(), PRODUCT_NOT_FOUND, null),
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
 
    @ExceptionHandler({ ProductSKUMismatchException.class, 
      ConstraintViolationException.class, ProductAlreadyExistException.class,
      DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBadRequest(
      Exception ex, WebRequest request) throws JsonProcessingException {
     	logger.info(ex.getClass().getName());
     	Product product = (Product) request.getAttribute("product", RequestAttributes.SCOPE_REQUEST);
    	ObjectMapper mapper = new ObjectMapper();
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST.name(), ex.getLocalizedMessage(), 
        		mapper.writeValueAsString(product).replace("\"", "'")), 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());

		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		ErrorResponse errorResp = new ErrorResponse(HttpStatus.BAD_REQUEST.name(), String.join(",",errors), 
        		null);
		return handleExceptionInternal(ex, errorResp, headers, HttpStatus.BAD_REQUEST, request);
	}
    
    public String convertWithStream(Map<String, String[]> map) {
        String mapAsString = map.keySet().stream()
          .map(key -> key + "=" + String.join(",", map.get(key)))
          .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }
}
