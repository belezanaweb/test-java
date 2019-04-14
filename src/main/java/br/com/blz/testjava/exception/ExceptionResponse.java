package br.com.blz.testjava.exception;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class ExceptionResponse {
    private String code;
    private ArrayList<String> errors = new ArrayList<>();
    private LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    public ExceptionResponse(ErrorMapping errorMapping) {
        this.code = String.valueOf(errorMapping.getCode());
    }

    public ExceptionResponse(ErrorMapping errorMapping, String message) {
        this.code = String.valueOf(errorMapping.getCode());
        this.addError(message);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCode() {
        return code;
    }

    public Iterable<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}
