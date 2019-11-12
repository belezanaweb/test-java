package br.com.blz.testjava.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
public class ApiException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private final ApiErrorResponse apiErrorResponse;
    
}