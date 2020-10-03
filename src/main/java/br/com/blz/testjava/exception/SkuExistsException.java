package br.com.blz.testjava.exception;

public class SkuExistsException extends BusinessException{

    public SkuExistsException(String message) {
        super(message);
    }
}
