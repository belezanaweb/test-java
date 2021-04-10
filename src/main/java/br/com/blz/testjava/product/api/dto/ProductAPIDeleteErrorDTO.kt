package br.com.blz.testjava.product.api.dto

sealed class ProductAPIDeleteOutputDTO(val success: Boolean)

data class ProductAPIDeleteOutputErrorDTO(val errors: List<String> = listOf()) : ProductAPIDeleteOutputDTO(false)
class ProductAPIDeleteOutputSuccessDTO : ProductAPIDeleteOutputDTO(true)
