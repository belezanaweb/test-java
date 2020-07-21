package br.com.blz.testjava.product.util;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)
public class ProductException extends RuntimeException {

private static final long serialVersionUID = -6386792487252812136L;
	
	private String uuid;
	private String erro;
	
	public ProductException() {}

	public ProductException(final String erro) {
		super(erro);
		UUID id = UUID.randomUUID();
		this.uuid = id.toString();
		this.erro = erro;
	}
	
	public String getErro() {
		return erro;
	}
	
	public void setErro(String erro) {
		this.erro = erro;
	}
	
	public String getUuid() {
		return uuid;
	}
	
}
