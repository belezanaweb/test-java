package br.com.blz.testjava.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


public class BaseExceptionHandler  extends ResponseEntityExceptionHandler{

    private static final ExceptionMapping DEFAULT_ERROR = new ExceptionMapping(
            "SERVER_ERROR",
            "Internal server error",
            INTERNAL_SERVER_ERROR);

    private final Logger log;
    private final Map<Class, ExceptionMapping> exceptionMappings = new HashMap<>();

    public BaseExceptionHandler(final Logger log) {
        this.log = log;

        registerMapping(
                MissingServletRequestParameterException.class,
                "MISSING_PARAMETER",
                "Missing request parameter",
                BAD_REQUEST);
        registerMapping(
                MethodArgumentTypeMismatchException.class,
                "ARGUMENT_TYPE_MISMATCH",
                "Argument type mismatch",
                BAD_REQUEST);
        registerMapping(
                HttpRequestMethodNotSupportedException.class,
                "METHOD_NOT_SUPPORTED",
                "HTTP method not supported",
                METHOD_NOT_ALLOWED);
        registerMapping(
                ServletRequestBindingException.class,
                "MISSING_HEADER",
                "Missing header in request",
                BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable ex, final HttpServletResponse response) {
        ExceptionMapping mapping = exceptionMappings.getOrDefault(ex.getClass(), DEFAULT_ERROR);

        response.setStatus(mapping.getStatus().value());

        log.error("{} ({}): {}", mapping.getMessage(), mapping.getCode(), ex.getMessage(), ex);

        return new ErrorResponse(mapping.getCode(), mapping.getMessage(), ex.getMessage());
    }

    protected void registerMapping(
            final Class<?> clazz,
            final String code,
            final String message,
            final HttpStatus status) {
        exceptionMappings.put(clazz, new ExceptionMapping(code, message, status));
    }

}