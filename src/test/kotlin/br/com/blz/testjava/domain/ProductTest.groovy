package br.com.blz.testjava.domain


import spock.lang.Specification

class ProductTest extends Specification {
	
	def "should return count"() {
		given:
			Product product = new Product(
					"123",
					"mac",
					false,
					[new Warehouse(1, "SP", Type.ECOMMERCE),
					 new Warehouse(1,"TO", Type.ECOMMERCE)]
			)
		when:
			def count = product.quantity()
		then:
			count == 2
	}
	
	def "should return product is marketable"() {
		given:
			Product product = new Product(
					"123",
					"mac",
					false,
					[new Warehouse(1, "SP", Type.ECOMMERCE),
					 new Warehouse(1, "TO", Type.ECOMMERCE)]
			)
		when:
			def marketable = product.isMarketable()
		then:
			marketable
	}
	
	def "should return product is not marketable"() {
		given:
			Product product = new Product(
					"123",
					"mac",
					false,
					[ new Warehouse(0, "SP", Type.ECOMMERCE),
					  new Warehouse(0, "TO", Type.ECOMMERCE) ]
			)
		when:
			def marketable = product.isMarketable()
		then:
			!marketable
	}
}
