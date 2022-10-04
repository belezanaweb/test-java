package br.com.blz.testjava.domain.exceptions;

public class DomainException extends NoStacktraceException {

    public DomainException(final String message) {
        super(message);
    }

}
