package br.com.blz.testjava.repository

import br.com.blz.testjava.exceptions.CustomNotFoundException
import br.com.blz.testjava.model.Product
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.io.ClassPathResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.Charset

@Service
class ProductImplRepository : ProductRepository {

  @Value("\${local.database.filename}")
  val localStorage: String = ""

  @Autowired
  private lateinit var mapper: ObjectMapper

  override fun save(entity: Product): Product? {
      return try {

        val localFile = ClassPathResource(localStorage)
        val json = localFile.file.readText(Charset.forName("UTF-8"))

        val products = mapper.readValue(json, object : TypeReference<MutableSet<Product>>() {})

        products.removeIf { product -> product.sku == entity.sku}

        products.add(entity)

        localFile.file.writeText(mapper.writeValueAsString(products))

        findBySku(entity.sku)
      } catch(_: Exception) {
        null
      }
  }

  override fun findBySku(sku: Long): Product {

    val localFile = ClassPathResource(localStorage)
    val json = localFile.file.readText(Charset.forName("UTF-8"))
    val products = mapper.readValue(json, object : TypeReference<List<Product>>() {})

    return products.find { product -> product.sku == sku }?:throw CustomNotFoundException("Sku not found")
  }

  override fun findAll(): List<Product?> {

    val localFile = ClassPathResource(localStorage)
    val json = localFile.file.readText(Charset.forName("UTF-8"))
    return mapper.readValue(json, object : TypeReference<List<Product>>() {}).sortedBy { it.sku }
  }

  override fun delete(entity: Product) {

    val localFile = ClassPathResource(localStorage)
    val json = localFile.file.readText(Charset.forName("UTF-8"))

    val products = mapper.readValue(json, object : TypeReference<MutableList<Product>>() {})
        products.removeIf {product -> product.sku == entity.sku}
    localFile.file.writeText(mapper.writeValueAsString(products))
  }

  override fun deleteAll() {

    val localFile = ClassPathResource(localStorage)
    localFile.file.writeText(mapper.writeValueAsString(emptyList<Product>()))
  }
}
