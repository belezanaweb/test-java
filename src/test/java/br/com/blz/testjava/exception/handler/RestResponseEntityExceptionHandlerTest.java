package br.com.blz.testjava.exception.handler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

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
@RunWith(MockitoJUnitRunner.class)
public class RestResponseEntityExceptionHandlerTest {
	
	@InjectMocks
	RestResponseEntityExceptionHandler handler;
	
	@Mock
	WebRequest request;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void handleNullProduct() {
		final String message = "Product must not be null";
		ResponseEntity handleResponse = handler.handleNullProduct(new NullProductException(message), request);
		
		assertEquals(HttpStatus.PRECONDITION_FAILED, handleResponse.getStatusCode());
		
		ResponseError err = (ResponseError) handleResponse.getBody();
		assertEquals(message, err.getMessage());
	}
	
	@Test
	public void handleInvalidId() {
		final String message = "Invalid id";
		ResponseEntity handleResponse = handler.handleInvalidId(new InvalidIdException(message), request);
		
		assertEquals(HttpStatus.PRECONDITION_FAILED, handleResponse.getStatusCode());
		
		ResponseError err = (ResponseError) handleResponse.getBody();
		assertEquals(message, err.getMessage());
	}
	
	@Test
	public void handleInvalidProductName() {
		final String message = "Invalid product name";
		ResponseEntity handleResponse = handler.handleInvalidProductName(new InvalidProductNameException(message), request);
		
		assertEquals(HttpStatus.PRECONDITION_FAILED, handleResponse.getStatusCode());
		
		ResponseError err = (ResponseError) handleResponse.getBody();
		assertEquals(message, err.getMessage());
	}
	
	@Test
	public void handleQuantity() {
		final String message = "Invalid quantity";
		ResponseEntity handleResponse = handler.handleQuantity(new InvalidQuantityInventoryLinkException(message), request);
		
		assertEquals(HttpStatus.PRECONDITION_FAILED, handleResponse.getStatusCode());
		
		ResponseError err = (ResponseError) handleResponse.getBody();
		assertEquals(message, err.getMessage());
	}
	
	@Test
	public void handleInvalidTotalOfProducts() {
		final String message = "Invalid total of products";
		ResponseEntity handleResponse = handler.handleInvalidTotalOfProducts(new InvalidTotalProductQuantityException(message), request);
		
		assertEquals(HttpStatus.PRECONDITION_FAILED, handleResponse.getStatusCode());
		
		ResponseError err = (ResponseError) handleResponse.getBody();
		assertEquals(message, err.getMessage());
	}
	
	@Test
	public void handleSKUAlreadyInUse() {
		final String message = "ID already in use";
		ResponseEntity handleResponse = handler.handleSKUAlreadyInUse(new ProductIdAlreadyInUseException(message), request);
		
		assertEquals(HttpStatus.CONFLICT, handleResponse.getStatusCode());
		
		ResponseError err = (ResponseError) handleResponse.getBody();
		assertEquals(message, err.getMessage());
	}
	
	@Test
	public void handleUnableToGetQuantity() {
		final String message = "Unable to get items quantity";
		ResponseEntity handleResponse = handler.handleUnableToGetQuantity(new UnableToGetItemsQuantityException(message), request);
		
		assertEquals(HttpStatus.BAD_REQUEST, handleResponse.getStatusCode());
		
		ResponseError err = (ResponseError) handleResponse.getBody();
		assertEquals(message, err.getMessage());
	}
	
	@Test
	public void handleProductNotExistent() {
		final String message = "Product not found";
		ResponseEntity handleResponse = handler.handleProductNotExistent(new ProductNotExistentException(message), request);
		
		assertEquals(HttpStatus.NOT_FOUND, handleResponse.getStatusCode());
		
		ResponseError err = (ResponseError) handleResponse.getBody();
		assertEquals(message, err.getMessage());
	}
}
