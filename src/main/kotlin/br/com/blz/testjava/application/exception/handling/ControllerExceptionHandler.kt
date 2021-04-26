package br.com.blz.testjava.application.exception.handling

import br.com.blz.testjava.application.exception.DataConstraintException
import br.com.blz.testjava.application.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllerExceptionHandler {

  @ExceptionHandler(NotFoundException::class)
  fun notFound(exception: NotFoundException, request: HttpServletRequest): ResponseEntity<StandardError> {
    val status = HttpStatus.INTERNAL_SERVER_ERROR
    val error = getStandardError(exception, request, "Data Integrity", status)
    return ResponseEntity.status(status).body(error)
  }

  @ExceptionHandler(DataConstraintException::class)
  fun constraint(exception: DataConstraintException, request: HttpServletRequest): ResponseEntity<StandardError> {
    val status = HttpStatus.INTERNAL_SERVER_ERROR
    val error = getStandardError(exception, request, "Data Constraint", status, exception.errors)
    return ResponseEntity.status(status).body(error)
  }

  private fun getStandardError(
      exception: RuntimeException,
      request: HttpServletRequest,
      errorType: String,
      status: HttpStatus,
      fieldErrors: List<FieldError> = mutableListOf()
  ): StandardError {
    return StandardError(
      System.currentTimeMillis(),
      status.value(),
      errorType,
      fieldErrors,
      exception.message,
      request.requestURI
      )
  }
}
