package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Produto não encontrado")
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3145359320159692645L;

}
