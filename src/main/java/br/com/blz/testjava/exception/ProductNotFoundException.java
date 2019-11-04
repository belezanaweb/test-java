package br.com.blz.testjava.exception;

public class ProductNotFoundException  extends ApplicationException {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
