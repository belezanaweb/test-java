package br.com.blz.testjava.application.exception.handling

data class StandardError(
  val timestamp: Long,
  val status: Int,
  val error: String,
  val message: String? = null,
  val path: String
)
