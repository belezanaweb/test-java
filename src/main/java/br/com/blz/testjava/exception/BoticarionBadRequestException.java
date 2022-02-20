package br.com.blz.testjava.exception;

public class BoticarionBadRequestException extends RuntimeException {

    public BoticarionBadRequestException(String message){
        super(message);
    }
}
