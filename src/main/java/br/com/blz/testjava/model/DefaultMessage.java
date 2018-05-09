package br.com.blz.testjava.model;

import lombok.Data;

@Data
public class DefaultMessage {

    private String message;

    public DefaultMessage(String message) {
        this.message = message;
    }
}
