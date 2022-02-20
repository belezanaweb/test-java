package br.com.blz.testjava.exception;

public class BoticarioNotFoundException extends RuntimeException {

    public BoticarioNotFoundException(String message){
        super(message);
    }
}
