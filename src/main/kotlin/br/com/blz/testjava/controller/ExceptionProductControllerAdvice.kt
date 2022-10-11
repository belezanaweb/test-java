package br.com.blz.testjava.controller

import br.com.blz.testjava.exception.ProductAlreadyExistsException
import br.com.blz.testjava.exception.ProductNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionProductControllerAdvice {

  @ExceptionHandler
  fun handleProductNotFoundException(ex: ProductNotFoundException): ResponseEntity<String> {
    return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
  }

  @ExceptionHandler
  fun handleProductAlreadyExistsException(ex: ProductAlreadyExistsException): ResponseEntity<String> {
    return ResponseEntity(ex.message, HttpStatus.CONFLICT)
  }
}
