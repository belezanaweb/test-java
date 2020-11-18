package br.com.blz.testjava.domain.exception;

public class ProductAlreadyExistsError extends RuntimeException {

  public ProductAlreadyExistsError(String message) {
    super(message);
  }

}
