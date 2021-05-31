package br.com.blz.testjava.repository

import br.com.blz.testjava.model.Product
import org.springframework.stereotype.Service

@Service
object ProductRepository {
    private val products: MutableList<Product> = mutableListOf()

    fun save(product: Product) = remove(product)
            .let { products.add(product) }
            .let { product }
    fun remove(product: Product) = products.removeIf { existent -> existent.sku == product.sku }

    fun findById(sku: Long): Product? = products.find { product -> product.sku == sku }

    fun findProductById (sku: Long, product: Product): Product? {

        for (productList in products) {
            if (productList.sku === product.sku) {
                calculaInventoryQuantity(product)
                return save(product)
            }
        }
        return null
    }

    fun findAll() = products.toMutableList()

    fun calculaInventoryQuantity(product: Product) {

        var qtd = 0

        for (warehouses in product.inventory?.warehouses!!) {
            qtd += warehouses.quantity
            product.inventory!!.quantity = qtd
        }

        if (product.inventory!!.quantity!! > 0) product.isMarketable = true
    }
}

private operator fun Int.invoke(qtd: Int) {

}
