package br.com.blz.testjava.base.exceptions;

public class DocumentNotExistsException extends RuntimeException {

    public DocumentNotExistsException(String id) {
        super(String.format("The Document: %s not exists", id));
    }
}
