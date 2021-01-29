package br.com.blz.testjava.controller

import br.com.blz.testjava.exception.ProductAlreadyExistsException
import br.com.blz.testjava.exception.ProductNotFoundException
import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ApiControllerAdvice : ResponseEntityExceptionHandler() {

  private val log = KotlinLogging.logger {}

  public override fun handleMissingServletRequestParameter(
    ex: MissingServletRequestParameterException,
    headers: HttpHeaders,
    status: HttpStatus,
    request: WebRequest
  ): ResponseEntity<Any> {
    return ResponseEntity.badRequest().body("The following request param is missing: ${ex.parameterName}")
  }

  @ExceptionHandler(MissingRequestHeaderException::class)
  fun missingReqHeaders(ex: MissingRequestHeaderException): ResponseEntity<Any> {
    return ResponseEntity.badRequest()
      .body("The following request header param is missing: ${ex.headerName}")
  }

  override fun handleHttpMessageNotReadable(
    e: HttpMessageNotReadableException,
    headers: HttpHeaders,
    status: HttpStatus,
    request: WebRequest
  ): ResponseEntity<Any> {
    return ResponseEntity.badRequest()
      .body("The content of request body is invalid")
  }

  @ExceptionHandler(ProductNotFoundException::class, ProductAlreadyExistsException::class)
  fun handleException(e: Exception): ResponseEntity<Any> {
    logException(e)
    val responseHeaders = HttpHeaders()
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).body("Unexpected Error")
  }

  @ExceptionHandler(Exception::class)
  fun handleUnknownError(e: Exception): ResponseEntity<Any> {
    logException(e)
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal system error")
  }

  private fun logException(ex: Exception) {
    log.error("[Uncaught Exception] - Error caught stacktrace", ex);
  }
}
