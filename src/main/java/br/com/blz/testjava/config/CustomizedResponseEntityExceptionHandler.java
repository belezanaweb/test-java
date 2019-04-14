package br.com.blz.testjava.config;

import br.com.blz.testjava.dto.response.ErrorResponse;
import br.com.blz.testjava.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class CustomizedResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
            new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @ExceptionHandler(ExistingProductException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity handleConflict(ExistingProductException ex) {
        ErrorResponse errorResponse = new ErrorResponse(messageSource().getMessage("validator.product.sku.existing", null, null));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(GenericErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerError(GenericErrorException ex) {
        return createErrorResponse(ex);
    }

    @ExceptionHandler(NotFoundProductException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundElement(NotFoundProductException ex) {
        return createErrorResponse(ex);
    }

    private ErrorResponse createErrorResponse(Throwable ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(ConstraintViolationException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorMapping.VALIDATION_ERROR);
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            exceptionResponse.addError(constraintViolation.getMessage());
        }
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
