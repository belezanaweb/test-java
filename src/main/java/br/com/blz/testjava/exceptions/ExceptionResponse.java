package br.com.blz.testjava.exceptions;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;
    private String uri;

    public ExceptionResponse(Date timestamp, String uri, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.uri = uri;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
