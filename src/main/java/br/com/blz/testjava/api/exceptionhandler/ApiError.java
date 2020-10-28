package br.com.blz.testjava.api.exceptionhandler;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.RequiredArgsConstructor;

@JsonAutoDetect(fieldVisibility = ANY)
@RequiredArgsConstructor
public class ApiError {

	private final String code;
    private final String message;

}
