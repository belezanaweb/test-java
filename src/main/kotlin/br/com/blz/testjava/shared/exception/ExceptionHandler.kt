package br.com.blz.testjava.shared.exception

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

  class Erro(val clientMsg: String, val devMsg: String)

  @Autowired
  lateinit var messageSource: MessageSource

  @Override
  override fun handleHttpMessageNotReadable(
    ex: HttpMessageNotReadableException,
    headers: HttpHeaders,
    status: HttpStatus,
    request: WebRequest,
  ): ResponseEntity<Any> {
    val clientMsg = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale())
    val devMsg = ex.cause?.toString() ?: ex.toString()
    val erros: List<Erro> = listOf(Erro(clientMsg, devMsg))
    return this.handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request)
  }

  @Override
  override fun handleMethodArgumentNotValid(
    ex: MethodArgumentNotValidException,
    headers: HttpHeaders, status: HttpStatus, request: WebRequest,
  ): ResponseEntity<Any> {

    val erros = createErrorList(ex.bindingResult)
    return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request)
  }

  private fun createErrorList(bindingResult: BindingResult): List<Erro> {
    val erros = ArrayList<Erro>()

    for (fieldError in bindingResult.fieldErrors) {
      val clientMsg = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())
      val devMsg = fieldError.toString()
      erros.add(Erro(clientMsg, devMsg))
    }
    return erros
  }

  @ExceptionHandler(Exception::class)
  fun handleEmptyResultException(
    ex: Exception,
    request: WebRequest,
  ): ResponseEntity<Any> {
    val clientMsg = ex.localizedMessage
    val devMsg = ex.toString()
    val erros = listOf(Erro(clientMsg, devMsg))
    return handleExceptionInternal(ex, erros, HttpHeaders(), HttpStatus.NOT_FOUND, request)
  }
}
