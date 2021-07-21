package br.com.blz.testjava.model;

public class Response {

    private final long idMessage;
    private final String message;
    private final Object content;

    public Response(long idMessage, String message, Object content) {
        this.idMessage = idMessage;
        this.message = message;
        this.content = content;
    }

    public long getIdMessage() {
        return idMessage;
    }

    public String getMessage() {
        return message;
    }

    public Object getContent() {
        return content;
    }
}
