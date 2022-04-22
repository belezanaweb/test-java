package br.com.blz.testjava.exceptions

import br.com.blz.testjava.controller.response.ErrorResponse
import br.com.blz.testjava.controller.response.FieldErrorResponse
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.regex.Matcher
import java.util.regex.Pattern


@ControllerAdvice
class ErrorHandleController {

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {

    val errors = mutableListOf<FieldErrorResponse>()

    ex.bindingResult.fieldErrors.map { fieldError ->
      errors.add(FieldErrorResponse(
        message = fieldError.defaultMessage?: "invalid",
        field = fieldError.field
      ))
    }

    val error = ErrorResponse(
      message = "validation fail",
      httpCode = HttpStatus.BAD_REQUEST.value(),
      errors = errors.toList()
    )

    return ResponseEntity(error, HttpStatus.BAD_REQUEST)
  }

  @ExceptionHandler(CustomNotFoundException::class)
  fun handleCustomNotFoundException(ex: CustomNotFoundException): ResponseEntity<ErrorResponse> {

    val error = ErrorResponse(
      message = ex.message,
      httpCode = HttpStatus.NOT_FOUND.value(),
      null
    )

    return ResponseEntity(error, HttpStatus.NOT_FOUND)
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException::class)
  fun handleBodyExceptions(
    ex: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
    val enumMessage = Pattern.compile("values accepted for Enum class: \\[([^\\]])\\]")
    if (ex.cause != null && ex.cause is InvalidFormatException) {
      val match: Matcher = enumMessage.matcher((ex.cause as InvalidFormatException).message)

      if (match.matches()) {
        val enumError = ErrorResponse(message = "value should be: " + match.group(1), httpCode = HttpStatus.BAD_REQUEST.value(), null)
        return ResponseEntity(enumError, HttpStatus.BAD_REQUEST)
      }
    }
    val error = ErrorResponse(
      message = ex.message,
      httpCode = HttpStatus.BAD_REQUEST.value(),
      null
    )

    return ResponseEntity(error, HttpStatus.BAD_REQUEST)
  }


}
