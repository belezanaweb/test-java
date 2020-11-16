package br.com.blz.testjava.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Data
public class ProductAlreadyExistException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
    public ProductAlreadyExistException(String resourceName, String fieldName, Integer fieldValue) {
        super(String.format("%s already exist with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
