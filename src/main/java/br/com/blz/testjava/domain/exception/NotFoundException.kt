package br.com.blz.testjava.domain.exception

class NotFoundException(override val message: String?): Exception(message) {
}
