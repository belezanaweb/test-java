package br.com.blz.testjava.contract.response;

public class ErrorMessageResponse {

    public String message;

    public ErrorMessageResponse() {
    }

    public ErrorMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
