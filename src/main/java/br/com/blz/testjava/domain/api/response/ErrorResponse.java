package br.com.blz.testjava.domain.api.response;

import java.util.List;

public class ErrorResponse {

    private List<ErrorDetail> errors;

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetail> errors) {
        this.errors = errors;
    }

    public static class ErrorDetail {
        private String field;
        private String cause;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }
    }
}
