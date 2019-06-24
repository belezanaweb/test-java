package br.com.blz.testjava.domain

data class Product(
	val sku: String,
	val name: String,
	val marketable: Boolean,
	val warehouses: List<Warehouse>
) {

	fun isMarketable(): Boolean = count() > 0

	fun quantity(): Int = count()

	private fun count(): Int {
		return warehouses
			.map(Warehouse::quantity)
			.reduce { sum, element -> sum + element }
	}
}