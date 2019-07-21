package br.com.blz.testjava.domain.api.request.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static br.com.blz.testjava.domain.api.request.validation.WarehouseTypeValidator.ECOMMERCE;
import static br.com.blz.testjava.domain.api.request.validation.WarehouseTypeValidator.PHYSICAL_STORE;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = WarehouseTypeValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface WarehouseType {

    String message() default "allowed values [" + ECOMMERCE + ", " + PHYSICAL_STORE + "]";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
