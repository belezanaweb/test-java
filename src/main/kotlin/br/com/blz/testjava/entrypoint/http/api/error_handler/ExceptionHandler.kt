package br.com.blz.testjava.entrypoint.http.api.error_handler

import br.com.blz.testjava.domain.exception.ProductAlreadyExistsException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handler(ex: Exception): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex::class.simpleName!!, ex.message!!, emptyMap())
        return ResponseEntity<ErrorResponse>(body, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(ProductAlreadyExistsException::class)
    fun handler(ex: ProductAlreadyExistsException): ResponseEntity<ErrorResponse> {
        val body = ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex::class.simpleName!!, ex.message!!, emptyMap())
        return ResponseEntity<ErrorResponse>(body, HttpStatus.BAD_REQUEST)
    }

}
