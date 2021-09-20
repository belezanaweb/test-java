package br.com.blz.testjava.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {

  @ExceptionHandler(value = [(Exception::class)])
  fun handleUserAlreadyExists(ex: Exception,request: WebRequest): ResponseEntity<String> {
    return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
  }
}
