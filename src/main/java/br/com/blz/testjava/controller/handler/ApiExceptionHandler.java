package br.com.blz.testjava.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.blz.testjava.controller.handler.ResponseObject.ResponseError;
import br.com.blz.testjava.exceptions.BusinessException;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler({BusinessException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseObject businessExceptionHandler(BusinessException ex) {
    ResponseError error = new ResponseError();
    error.setMessage(ex.getMessage());
    return new ResponseBuilder<>().withError(error).build();
  }
}
