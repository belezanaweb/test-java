package br.com.blz.testjava.model.exceptions;

public class ProductUniqueKeyException extends RuntimeException{

    public ProductUniqueKeyException(String message){
        super(message);
    }
}
