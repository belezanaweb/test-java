package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends BusinessException {

    //TODO Verificar necessidade
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }
}
