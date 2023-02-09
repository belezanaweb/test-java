package br.com.blz.testjava.common.exceptions;

import javassist.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
