package br.com.blz.testjava.controller.response

data class ErrorResponse (val message: String?, val httpCode: Int, val errors: List<FieldErrorResponse?>? = emptyList())
