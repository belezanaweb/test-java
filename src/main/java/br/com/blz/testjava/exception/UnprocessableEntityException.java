package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends BusinessException {

    //TODO Verificar necessidade
    private static final long serialVersionUID = 1L;

    public UnprocessableEntityException(String message) {
        super(message);
    }
}
