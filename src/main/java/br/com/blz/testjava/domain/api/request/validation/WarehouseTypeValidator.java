package br.com.blz.testjava.domain.api.request.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class WarehouseTypeValidator implements ConstraintValidator<WarehouseType, String> {

    static final String ECOMMERCE = "ECOMMERCE";
    static final String PHYSICAL_STORE = "PHYSICAL_STORE";

    private List<String> types = Arrays.asList(ECOMMERCE, PHYSICAL_STORE);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null ||
            value.split(" ").length == 0 ||
            value.split(" ")[0].isEmpty() ||
            types.contains(value.toUpperCase());
    }
}
