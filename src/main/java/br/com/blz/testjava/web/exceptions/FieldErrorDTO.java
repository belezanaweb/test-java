package br.com.blz.testjava.web.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldErrorDTO {

    private String field;
    private String message;

    public static FieldErrorDTO from(FieldError fieldError) {
        return new FieldErrorDTO(fieldError.getField(), fieldError.getDefaultMessage());
    }

}
