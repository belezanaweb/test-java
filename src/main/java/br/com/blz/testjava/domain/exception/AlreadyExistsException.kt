package br.com.blz.testjava.domain.exception

class AlreadyExistsException(override val message: String?): Exception(message) {
}
