package br.com.blz.testjava.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({
        ProductBusinessException.class,
        ProductNotFoundException.class
    })
    private ResponseEntity<ErrorResponse> buildBusinessException(Exception ex, WebRequest request) {
        HttpStatus statusCode = ex instanceof ProductNotFoundException ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;

        ErrorResponse error = new ErrorResponse();
        error.setError(ex.getMessage());
        error.setStatus(statusCode.value());

        return new ResponseEntity<>(error, statusCode);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    ResponseEntity<Object> onConstraintValidationException(
        ConstraintViolationException ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        List<String> errors = ex.getConstraintViolations()
            .stream()
            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
            .collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());

        //Get all fields errors
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorResponse> buildUnknownException(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setError("Sorry!! We are unable to process your request. Please try again later.");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

