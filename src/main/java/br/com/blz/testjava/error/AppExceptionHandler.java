package br.com.blz.testjava.error;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.blz.testjava.error.ErrorResponse.AppError;
import br.com.blz.testjava.service.exception.BusinessException;

@RestControllerAdvice
public class AppExceptionHandler {

	private static final String NO_MESSSAGE_AVAILABLE = "No message available";

	private final MessageSource apiErrorMessageSource;

	public AppExceptionHandler(@Autowired MessageSource apiErrorMessageSource) {
		this.apiErrorMessageSource = apiErrorMessageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleNotValidException(MethodArgumentNotValidException exception, Locale locale) {

		Stream<ObjectError> errors = exception.getBindingResult().getAllErrors().stream();

		List<AppError> appErrors = errors
				.map(ObjectError::getDefaultMessage)
				.map(code -> toAppError(code, locale))
				.collect(toList());

		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, appErrors);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception, Locale locale) {
		final String errorCode = exception.getCode();
		final HttpStatus status = exception.getStatus();
		final ErrorResponse errorResponse = ErrorResponse.of(status, toAppError(errorCode, locale));
		return ResponseEntity.badRequest().body(errorResponse);
	}

	private AppError toAppError(String code, Locale locale) {
		String message;
		try {
			message = apiErrorMessageSource.getMessage(code, null, locale);
		} catch (NoSuchMessageException e) {
			message = NO_MESSSAGE_AVAILABLE;
		}

		return new AppError(code, message);
	}

}
