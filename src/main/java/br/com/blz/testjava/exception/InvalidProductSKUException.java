package br.com.blz.testjava.exception;

import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@ResponseStatus(BAD_REQUEST)
public class InvalidProductSKUException extends Exception implements Serializable {

    private static final long serialVersionUID = -3869354463356184732L;

    public static final String MSG = "SKU nao pode ser nulo";

    public InvalidProductSKUException() {
        super(MSG);
    }
}
