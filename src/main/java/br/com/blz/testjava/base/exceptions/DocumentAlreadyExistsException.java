package br.com.blz.testjava.base.exceptions;

public class DocumentAlreadyExistsException extends RuntimeException {

    public DocumentAlreadyExistsException(String id) {
        super(String.format("The Document: %s already exists", id));
    }
}
