package br.com.blz.testjava.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
@AllArgsConstructor
public class ValidationService {

    private Validator validator;

    public <T> void validate(T source) throws ConstraintViolationException {
        Set<ConstraintViolation<T>> violations = validator.validate(source);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
