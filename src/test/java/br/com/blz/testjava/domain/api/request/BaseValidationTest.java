package br.com.blz.testjava.domain.api.request;

import javax.validation.Validation;
import javax.validation.Validator;

public abstract class BaseValidationTest {

    static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
}
