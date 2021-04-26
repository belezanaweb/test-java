package br.com.blz.testjava.application.exception.handling

data class StandardError(
  val timestamp: Long,
  val status: Int,
  val error: String,
  val errors: List<FieldError> = mutableListOf(),
  val message: String? = null,
  val path: String
)
