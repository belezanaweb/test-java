package br.com.blz.testjava.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class NullProductException extends Exception implements Serializable {

    private static final long serialVersionUID = -3869354463356184732L;
    public static final String MSG = "Produto não pode ser nulo";

    public NullProductException() {
        super(MSG);
    }
}
