package br.com.blz.testjava.exception

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class HttpErrorExceptionHandler {

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(DomainBusinessException::class)
    @ResponseBody
    fun tratarErroNegocio(erro: DomainBusinessException): String? {
        return erro.message
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException::class)
    @ResponseBody
    fun tratarErroEntidadeNaoEncontrada(erro: EntityNotFoundException): String? {
        return erro.message
    }
}
