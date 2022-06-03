package br.com.blz.testjava.core.exceptions;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {

    private String code;

    public ProductException(String code, String message) {
        super(message);
        this.code = code;
    }

}
