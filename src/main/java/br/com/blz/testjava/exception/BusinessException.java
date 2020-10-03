package br.com.blz.testjava.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}
