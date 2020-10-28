package br.com.blz.testjava.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 7886299060740594130L;
	
	private final String code;
    private final HttpStatus status;

}
