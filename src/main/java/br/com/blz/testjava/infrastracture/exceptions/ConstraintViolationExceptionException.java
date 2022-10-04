package br.com.blz.testjava.infrastracture.exceptions;

import br.com.blz.testjava.domain.Identifier;
import br.com.blz.testjava.domain.exceptions.NoStacktraceException;

public class ConstraintViolationExceptionException extends NoStacktraceException {

    protected ConstraintViolationExceptionException(final String message) {
        super(message, null);
    }

    public static ConstraintViolationExceptionException with(final String entityName, final Identifier id, String attributeName) {
        final var error = "%s com %s %d já cadastrado".formatted(entityName, attributeName, id.getValue());
        return new ConstraintViolationExceptionException(error);
    }
}
