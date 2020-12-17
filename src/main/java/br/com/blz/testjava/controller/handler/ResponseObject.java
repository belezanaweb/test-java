package br.com.blz.testjava.controller.handler;

public class ResponseObject {

    public static class ResponseError {

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    private ResponseError error;

    public ResponseError getError() {
        return error;
    }

    public void setError(ResponseError error) {
        this.error = error;
    }
}
