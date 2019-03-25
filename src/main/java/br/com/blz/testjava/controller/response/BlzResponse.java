package br.com.blz.testjava.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlzResponse<T> {

    @JsonProperty
    private Integer httpStatus;

    @JsonProperty
    private String message;

    @JsonProperty
    private T response;

    public BlzResponse(Integer httpStatus, T response) {
        this(httpStatus, null, response);
    }

    public BlzResponse(Integer httpStatus, String message) {
        this(httpStatus, message, null);
    }

    public BlzResponse(Integer httpStatus, String message, T response) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return this.response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
}
