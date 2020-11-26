package br.com.blz.testjava.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class NonExistingProductException extends Exception implements Serializable {

    private static final long serialVersionUID = 7433114810050275895L;

    public static final String MSG = "Produto não existe [SKU: %s]";

    public NonExistingProductException(String sku) {
        super(format(MSG, sku));
    }
}
