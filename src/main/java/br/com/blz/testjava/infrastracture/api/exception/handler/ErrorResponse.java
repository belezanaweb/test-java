package br.com.blz.testjava.infrastracture.api.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(int status, String message) {
}
