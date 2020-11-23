package br.com.blz.testjava.exception;

import br.com.blz.testjava.dto.support.ResponseMapEntry;

import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@ControllerAdvice
public class DefaultControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String ERROR_MSG = "errorMsg";

    @Autowired
    @NonNull
    private MessageSource messageSource;

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<ResponseMapEntry> exceptions(CustomRuntimeException e) {
        logarExcecaoSemStackTrace(e);
        var message = messageSource.getMessage(e.getValidationMsg().getKey(), e.getParams(), Locale.getDefault());
        return new ResponseEntity<>(ResponseMapEntry.of(ERROR_MSG, message), BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseMapEntry> exceptions(IllegalArgumentException e) {
        logarExcecaoSemStackTrace(e);
        return new ResponseEntity<>(ResponseMapEntry.of(ERROR_MSG, e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Exception> conversionFailedException(ConversionFailedException e) {
        logarExcecaoSemStackTrace(e);
        return new ResponseEntity<>(e, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Throwable> exception(Throwable e) {
        var cause = NestedExceptionUtils.getMostSpecificCause(e);
        logarExcecaoSemStackTrace(cause);
        return new ResponseEntity<>(cause, INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ResponseMapEntry.of(ERROR_MSG, e.getMessage()), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        var listErrors = e.getBindingResult()
                          .getFieldErrors()
                          .stream().sorted(Comparator.comparing(FieldError::getField))
                          .map(error -> error.getField() + ": " + error.getDefaultMessage())
                          .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseMapEntry.of(ERROR_MSG, e.getBindingResult().getObjectName() + " -> " + listErrors.toString()),
                                    headers, status);
    }

    private static void logarExcecaoSemStackTrace(Throwable e, String... errorsMsg) {
        var mensagem = errorsMsg.length == 1 ? errorsMsg[0] : e.getMessage();
        esconderStackTrace(e);
        log.error(mensagem, e);
    }

    private static void esconderStackTrace(Throwable e) {
        e.setStackTrace(new StackTraceElement[]{});
    }

}
