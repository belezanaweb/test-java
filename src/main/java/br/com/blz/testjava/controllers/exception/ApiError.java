package br.com.blz.testjava.controllers.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;

@Getter
public class ApiError {

    private int status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private String message;

    private ApiError() {
        this.timestamp = new Date();
    }

    public ApiError(int status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
    }

}
