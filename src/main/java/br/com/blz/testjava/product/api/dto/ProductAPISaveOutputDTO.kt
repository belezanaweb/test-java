package br.com.blz.testjava.product.api.dto

sealed class ProductAPISaveOutputDTO(val success: Boolean = false)
class ProductAPISaveOutputDTOSuccess : ProductAPISaveOutputDTO(true)
data class ProductAPISaveOutputDTOError(val errors: List<String> = listOf()) : ProductAPISaveOutputDTO(false)
