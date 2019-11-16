package br.com.blz.testjava.controller.response;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {
    
    private String message;
    private int code;
    
    public ExceptionResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }
    
    public ExceptionResponse() {
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
}
