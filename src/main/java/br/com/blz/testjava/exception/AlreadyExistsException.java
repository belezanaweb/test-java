package br.com.blz.testjava.exception;



public class AlreadyExistsException extends Exception {

    public AlreadyExistsException(String message){
        super(String.format("product duplicate with Sku : ", message));
    }
}
