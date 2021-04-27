package br.com.blz.testjava.application.exception

import br.com.blz.testjava.application.exception.handling.FieldError
import org.springframework.validation.BindingResult

class DataConstraintException(message: String) : RuntimeException(message) {

  val errors = mutableListOf<FieldError>()

  constructor(br: BindingResult): this("Data constraint exception") {
    for (error in br.allErrors) {
      val field = error.codes?.firstOrNull()?.replace(error.code + ".", "")
      if (field != null) {
        errors.add(FieldError(error.objectName, field, error.code!!))
      }
    }
  }
}
