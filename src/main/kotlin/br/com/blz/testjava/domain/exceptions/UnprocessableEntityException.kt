package br.com.blz.testjava.domain.exceptions

class UnprocessableEntityException(override val message: String) : RuntimeException(message)