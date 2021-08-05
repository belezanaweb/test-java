package com.example.testkotlin.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.CONFLICT)
class ProductAlreadyExistsException (override val message:String?) : Exception(message)