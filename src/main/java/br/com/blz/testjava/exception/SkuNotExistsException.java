package br.com.blz.testjava.exception;

public class SkuNotExistsException extends BusinessException{

    public SkuNotExistsException(String message) {
        super(message);
    }
}
