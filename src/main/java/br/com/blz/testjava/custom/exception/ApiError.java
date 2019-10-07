package br.com.blz.testjava.custom.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
        @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyyy HH:mm:ss")
        private Date          timestamp;
        private Integer       status;
        private String        error;
        private Integer       internalErrorCode;
        private String        exception;
        private String        message;
        private String        path;

        private ApiError() {
            timestamp = new Date();
        }

        public ApiError(HttpStatus status) {
            this();
            this.status = status.value();
            error = status.getReasonPhrase();
        }

        public ApiError(HttpStatus status, Throwable exception) {
            this(status);
            this.exception = exception.getClass().getCanonicalName();
            this.message = exception.getLocalizedMessage();
        }

        public ApiError(HttpStatus status, Throwable exception, String message) {
            this(status);
            this.exception = exception.getClass().getCanonicalName();
            this.message = message;
        }
    }

