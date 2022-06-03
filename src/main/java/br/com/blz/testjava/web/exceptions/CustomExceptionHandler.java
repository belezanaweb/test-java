package br.com.blz.testjava.web.exceptions;

import br.com.blz.testjava.core.exceptions.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({ProductException.class})
    @ResponseStatus(BAD_REQUEST)
    public ErrorDTO handleProductException(final ProductException ex) {
        ErrorDTO errorDTO = ErrorDTO.from(BAD_REQUEST, ex.getMessage(), ex);

        log.error("error_handleProductException", errorDTO.toString());

        return errorDTO;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(BAD_REQUEST)
    public ErrorDTO handleHttpMessageNotReadableException(final HttpMessageNotReadableException ex) {
        ErrorDTO errorDTO = ErrorDTO.from(BAD_REQUEST, "Invalid Arguments", ex);

        log.error("error_handleHttpMessageNotReadableException", errorDTO.toString());

        return errorDTO;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(BAD_REQUEST)
    public ErrorDTO handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        List<FieldErrorDTO> fieldErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldErrorDTO::from)
                .collect(toList());

        ErrorDTO errorDTO = ErrorDTO.from(BAD_REQUEST, "Invalid Arguments", fieldErrors);

        log.error("error_handleMethodArgumentNotValidException", errorDTO.toString());

        return errorDTO;
    }

}
