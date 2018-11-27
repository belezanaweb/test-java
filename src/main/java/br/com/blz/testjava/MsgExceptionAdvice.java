package br.com.blz.testjava;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MsgExceptionAdvice {
  
  @ResponseBody
  @ExceptionHandler(MsgException.class)
  public ResponseEntity<MsgException> excecao(MsgException ex){
    return new ResponseEntity<MsgException>(ex, HttpStatus.resolve(ex.getHttpStatus()) );
  }
  
  @ResponseBody
  @ExceptionHandler(Exception.class)
  public ResponseEntity<MsgException> excecao(Exception ex){
    return excecao(new MsgException(ex));
  }
  
}
