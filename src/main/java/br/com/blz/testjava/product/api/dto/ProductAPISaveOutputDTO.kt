package br.com.blz.testjava.product.api.dto

sealed class ProductAPISaveOutputDTO(val success: Boolean = false)
class ProductAPISaveOutputSuccessDTO : ProductAPISaveOutputDTO(true)
data class ProductAPISaveOutputErrorDTO(val errors: List<String> = listOf()) : ProductAPISaveOutputDTO(false)
