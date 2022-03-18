package br.com.blz.testjava.entrypoint.http.api.error_handler

class ErrorResponse(
  val code: Int,
  val type: String,
  val message: String,
  val details: Map<String, Any>
)
