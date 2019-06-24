package br.com.blz.testjava.service

import br.com.blz.testjava.domain.Product
import br.com.blz.testjava.repository.ProductRepository
import spock.lang.Specification

class ProductServiceTest extends Specification {
	
	ProductService service
	Set<Product> products = new HashSet<>()
	ProductRepository repository = new ProductRepository(products)
	
	void cleanup() {
		products.removeAll()
	}
	
	void setup() {
		service = new ProductService(repository)
	}
	
	def "must find product by sku"() {
		given:
			Product product = new Product("123", "mac", false, [])
		when:
			service.create(product).block()
		then:
			Product result = service.findBySku("123").block()
			result.sku == "123"
			result.name == "mac"
			!result.marketable
	}
	
	def "must create product"() {
		given:
			Product product = new Product("123", "mac", false, [])
		when:
			service.create(product).block()
		then:
			Product result = service.findBySku("123").block()
			result.sku == "123"
			result.name == "mac"
			!result.marketable
	}
	
	def "must update product"() {
		given:
			String sku = '123'
			Product product = new Product("123", "mac", false, [])
			service.create(product)
		when:
			service.update(sku, new Product("123", "inoar", false, [])).block()
		then:
			Product result = service.findBySku("123").block()
			result.sku == "123"
			result.name == "inoar"
			!result.marketable
	}
	
	def "must delete product"() {
		given:
			Product product = new Product("123", "mac", false, [])
			service.create(product)
		when:
			service.delete('123').block()
		then:
			Product result = service.findBySku("123").block()
			result == null
	}
	
	def "should return list of products"() {
		given:
			Product product1 = new Product("123", "mac", false, [])
			Product product2 = new Product("124", "mac", false, [])
			Product product3 = new Product("125", "mac", false, [])
			repository.insert([product1, product2, product3] as Set<Product>)
		when:
			def products = service.list().collectList().block()
		then:
			products.size() == 3
	}
}
