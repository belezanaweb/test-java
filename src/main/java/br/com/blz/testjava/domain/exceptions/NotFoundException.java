package br.com.blz.testjava.domain.exceptions;


import br.com.blz.testjava.domain.AggregateRoot;
import br.com.blz.testjava.domain.Identifier;

public class NotFoundException extends DomainException {

    protected NotFoundException(final String message) {
        super(message);
    }

    public static NotFoundException with(final Class<? extends AggregateRoot<?>> aggregate, final Identifier id, String name) {
        final var error = "%s com %s %d não foi encontrado".formatted(aggregate.getSimpleName(), name, id.getValue());
        return new NotFoundException(error);
    }
}
