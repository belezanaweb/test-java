package br.com.blz.testjava.application.exception.handling

import br.com.blz.testjava.application.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllerExceptionHandler {

  @ExceptionHandler(NotFoundException::class)
  fun constraint(exception: NotFoundException, request: HttpServletRequest): ResponseEntity<StandardError> {
    val status = HttpStatus.INTERNAL_SERVER_ERROR
    val error = StandardError(
      System.currentTimeMillis(),
      status.value(),
      "Data Constraint",
      exception.message,
      request.requestURI
    )

    return ResponseEntity.status(status).body(error)
  }
}
