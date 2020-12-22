package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SkuAlreadyRegisteredException extends RuntimeException {
    private String code;

    public SkuAlreadyRegisteredException(String message) {
        super(message);
    }
}
