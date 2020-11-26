package br.com.blz.testjava.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class ExistingProductException extends Exception implements Serializable {

    private static final long serialVersionUID = 3815891264301349684L;
    public static final String MSG = "Produto já cadastrado [SKU: %s]";

    public ExistingProductException(String sku) {
        super(format(MSG, sku));
    }
}
