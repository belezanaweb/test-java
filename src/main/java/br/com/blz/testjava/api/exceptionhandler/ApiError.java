package br.com.blz.testjava.api.exceptionhandler;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@JsonAutoDetect(fieldVisibility = ANY)
@RequiredArgsConstructor
public class ApiError {

	private final String code;
    private final String message;

}
