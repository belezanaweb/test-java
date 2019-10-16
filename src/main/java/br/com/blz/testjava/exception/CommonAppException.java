package br.com.blz.testjava.exception;

import org.springframework.http.HttpStatus;

public class CommonAppException extends Exception {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public CommonAppException(String msg, HttpStatus status) {
        super(msg);
        this.setStatus(status);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
