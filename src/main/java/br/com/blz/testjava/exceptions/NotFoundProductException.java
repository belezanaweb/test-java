package br.com.blz.testjava.exceptions;

public class NotFoundProductException extends RuntimeException {

  public NotFoundProductException(final String message) {
    super(message);
  }

}
