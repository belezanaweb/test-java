package br.com.blz.testjava.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.blz.testjava.exception.InvalidIdException;
import br.com.blz.testjava.exception.InvalidProductNameException;
import br.com.blz.testjava.exception.InvalidQuantityInventoryLinkException;
import br.com.blz.testjava.exception.InvalidTotalProductQuantityException;
import br.com.blz.testjava.exception.NullProductException;
import br.com.blz.testjava.exception.ProductIdAlreadyInUseException;
import br.com.blz.testjava.exception.ProductNotExistentException;
import br.com.blz.testjava.exception.UnableToGetItemsQuantityException;
import br.com.blz.testjava.model.ResponseError;

@SuppressWarnings("rawtypes")
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value={NullProductException.class})
	protected ResponseEntity handleNullProduct(NullProductException e, WebRequest request) {
		return handleCustomException(e, request, HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(value={InvalidIdException.class})
	protected ResponseEntity handleInvalidId(InvalidIdException e, WebRequest request) {
		return handleCustomException(e, request, HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(value={InvalidProductNameException.class})
	protected ResponseEntity handleInvalidProductName(InvalidProductNameException e, WebRequest request) {
		return handleCustomException(e, request, HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(value={InvalidQuantityInventoryLinkException.class})
	protected ResponseEntity handleQuantity(InvalidQuantityInventoryLinkException e, WebRequest request) {
		return handleCustomException(e, request, HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(value={InvalidTotalProductQuantityException.class})
	protected ResponseEntity handleInvalidTotalOfProducts(InvalidTotalProductQuantityException e, WebRequest request) {
		return handleCustomException(e, request, HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(value={ProductIdAlreadyInUseException.class})
	protected ResponseEntity handleSKUAlreadyInUse(ProductIdAlreadyInUseException e, WebRequest request) {
		return handleCustomException(e, request, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value={UnableToGetItemsQuantityException.class})
	protected ResponseEntity handleUnableToGetQuantity(UnableToGetItemsQuantityException e, WebRequest request) {
		return handleCustomException(e, request, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value={ProductNotExistentException.class})
	protected ResponseEntity handleProductNotExistent(ProductNotExistentException e, WebRequest request) {
		return handleCustomException(e, request, HttpStatus.NOT_FOUND); 
	}

	private ResponseEntity handleCustomException(RuntimeException e, WebRequest request, HttpStatus status) {
		ResponseError err = new ResponseError();
		err.setCode(status.value());
		err.setMessage(e.getMessage());
		
		return handleExceptionInternal(e, err, new HttpHeaders(), status, request);
	}
}
