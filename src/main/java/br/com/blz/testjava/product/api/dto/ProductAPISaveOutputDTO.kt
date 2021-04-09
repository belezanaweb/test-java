package br.com.blz.testjava.product.api.dto

sealed class ProductAPISaveOutputDTO(val success: Boolean)
class ProductAPISaveOutputDTOSuccess : ProductAPISaveOutputDTO(true)
data class ProductAPISaveOutputDTOError(val errors: List<String>) : ProductAPISaveOutputDTO(false)
