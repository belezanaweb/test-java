package br.com.blz.testjava.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ApiErrors{


    public static ResponseEntity badRequest(String message) {
        return error(message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity notFound(String message) {
        return error(message, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity InternalError(String message) {
        return error(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity timeout(String message) {
        return error(message, HttpStatus.GATEWAY_TIMEOUT);
    }

    private static ResponseEntity error(String message, HttpStatus status) {

        final Map<Object, Object> model = new HashMap<>();
        model.put("error", status.getReasonPhrase());
        model.put("status", status.value());
        model.put("message", message);

        return new ResponseEntity(model, status);
    }

}
