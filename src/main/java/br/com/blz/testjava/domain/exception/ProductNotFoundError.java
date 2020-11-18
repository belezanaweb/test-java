package br.com.blz.testjava.domain.exception;

public class ProductNotFoundError extends RuntimeException {

  public ProductNotFoundError(String message) {
    super(message);
  }
}
