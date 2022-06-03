package br.com.blz.testjava.web.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@JsonInclude(NON_NULL)
@Getter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@ToString
public class ErrorDTO {

    private int code;
    private String error;
    private String message;
    private List<FieldErrorDTO> fieldErrors;
    private LocalDateTime timestamp;
    private String stackTrace;

    public static ErrorDTO from(final HttpStatus status, final String message, final List<FieldErrorDTO> fieldErrors) {
        return ErrorDTO.builder()
            .code(status.value())
            .error(status.getReasonPhrase())
            .message(message)
            .fieldErrors(fieldErrors)
            .timestamp(LocalDateTime.now())
            .build();
    }

    public static ErrorDTO from(final HttpStatus status, final String message, Exception ex) {
        return ErrorDTO.builder()
            .code(status.value())
            .error(status.getReasonPhrase())
            .message(message)
            .timestamp(LocalDateTime.now())
            .stackTrace(ex.getMessage())
            .build();
    }

}
