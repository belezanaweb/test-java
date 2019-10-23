package br.com.blz.testjava.exception;

public class ResourceAlreadyExistsException extends Exception {

    public ResourceAlreadyExistsException() {
        super("Resource already exists.");
    }
}
