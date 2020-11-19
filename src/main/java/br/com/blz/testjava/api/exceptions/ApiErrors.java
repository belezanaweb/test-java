package br.com.blz.testjava.api.exceptions;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.BindingResult;

public class ApiErrors {

    private List<String> errors;

    public ApiErrors(BindingResult bindingResult) {
        this.errors = new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> this.errors.add(error.getDefaultMessage()));
    }

    public List<String> getErrors() {
        return errors;
    }
}
