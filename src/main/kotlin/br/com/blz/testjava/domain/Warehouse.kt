package br.com.blz.testjava.domain

data class Warehouse(
	val quantity: Int,
	val locality: String,
	val type: Enum<Type>
)
