package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Produto com sku já cadastrado")
public class ProductAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -8295816887205261098L;

}
