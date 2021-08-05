package com.example.testkotlin.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

class ControllerAdviceRequestError: ResponseEntityExceptionHandler() {

    fun handleProductAlreadyExists(ex: ProductAlreadyExistsException)
            : ResponseEntity<ErrorDetails> {

        val errDetail = ErrorDetails(Date(), "Validation Failed", ex.message!!)
        return ResponseEntity(errDetail, HttpStatus.BAD_REQUEST)
    }

}