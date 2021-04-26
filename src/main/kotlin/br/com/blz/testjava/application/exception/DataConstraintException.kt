package br.com.blz.testjava.application.exception

import br.com.blz.testjava.application.exception.handling.FieldError
import org.springframework.validation.BindingResult

class DataConstraintException(message: String) : RuntimeException(message) {

  val errors = mutableListOf<FieldError>()

  constructor(br: BindingResult): this("Data constraint") {
    for (error in br.allErrors) {
      val field = error.codes!![0].replace(error.code + ".", "")
      errors.add(FieldError(error.objectName, field, error.code!!))
    }
  }
}
