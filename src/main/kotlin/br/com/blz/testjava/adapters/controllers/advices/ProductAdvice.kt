package br.com.blz.testjava.adapters.controllers.advices

import br.com.blz.testjava.domain.exceptions.UnprocessableEntityException
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@ControllerAdvice
@ResponseBody
class ProductAdvice(private val messageSource: MessageSource) {

    @ExceptionHandler(UnprocessableEntityException::class)
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    fun handler(exception: UnprocessableEntityException) =
       mapOf("message" to messageSource.getMessage(exception.message, null, Locale.getDefault()))

}