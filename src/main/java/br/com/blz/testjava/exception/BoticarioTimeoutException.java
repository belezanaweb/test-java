package br.com.blz.testjava.exception;

public class BoticarioTimeoutException extends RuntimeException {

    public BoticarioTimeoutException(String message){
        super(message);
    }
}
