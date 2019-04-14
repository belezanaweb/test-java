package br.com.blz.testjava.exception;

public enum ErrorMapping {
    VALIDATION_ERROR(1),
    ASSIGNER_NOT_FOUND(2),
    TOKEN_INVALID(3);

    private final int code;
    ErrorMapping(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
