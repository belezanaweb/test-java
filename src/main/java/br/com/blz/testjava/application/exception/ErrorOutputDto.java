package br.com.blz.testjava.application.exception;

public class ErrorOutputDto {

    private String message;

    public ErrorOutputDto(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
