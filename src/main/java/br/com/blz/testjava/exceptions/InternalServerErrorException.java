package br.com.blz.testjava.exceptions;

public class InternalServerErrorException extends RuntimeException {

  public InternalServerErrorException(final String message) {
    super(message);
  }

}
