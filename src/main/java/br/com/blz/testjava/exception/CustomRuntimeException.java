package br.com.blz.testjava.exception;

import java.util.Arrays;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ValidationMsg validationMsg;

    private final Object[] params;

    public CustomRuntimeException(ValidationMsg validationMsg, Object... params) {
        super(validationMsg.getKey() + ": " + Arrays.toString(params));
        this.validationMsg = validationMsg;
        this.params = params;
    }

}
