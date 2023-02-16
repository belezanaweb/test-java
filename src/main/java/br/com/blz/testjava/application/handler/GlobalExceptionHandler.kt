package br.com.blz.testjava.application.handler

import br.com.blz.testjava.domain.exception.AlreadyExistsException
import br.com.blz.testjava.domain.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

  @ExceptionHandler(value = [(AlreadyExistsException::class)])
  fun handleAlreadyExistsException(ex: Exception) = ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)

  @ExceptionHandler(value = [(NotFoundException::class)])
  fun handleNotFoundException(ex: Exception) = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
}
