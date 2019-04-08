package br.com.blz.testjava.config.advice;

import br.com.blz.testjava.exceptions.ConflictException;
import br.com.blz.testjava.exceptions.InternalServerErrorException;
import br.com.blz.testjava.exceptions.NotFoundProductException;
import br.com.blz.testjava.gateways.http.converter.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApplicationAdvice {

  @ExceptionHandler(ConflictException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponse handleConflict(ConflictException ex) {
    return createErrorResponse(ex);
  }

  @ExceptionHandler(InternalServerErrorException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleInternalServerError(InternalServerErrorException ex) {
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

}
