package br.com.blz.testjava.repository

import br.com.blz.testjava.exceptions.CustomNotFoundException
import br.com.blz.testjava.model.Product
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.Charset

@Service
class ProductImplRepository : ProductRepository {

  @Value("\${spring.local.database.filename}")
  val localStorage: String = ""

  private val mapper = jacksonObjectMapper()

  override fun save(entity: Product): Product? {

        try {
          val localFile = ClassPathResource(localStorage)
          val json = localFile.file.readText(Charset.forName("UTF-8"))

          val products: MutableList<Product?> = if(json.isNotBlank()) {
            mapper.readValue(json)
          } else {
            mutableListOf()
          }

          val index: Int = products.map { it?.sku }.indexOf(entity.sku)

          if(index != -1)
            products[index] = entity
          else
            products.add(entity)

          localFile.file.writeText(mapper.writeValueAsString(products))

          return findBySku(entity.sku)
        } catch (ex: Exception) {
          throw Exception("Internal server error")
        }
  }

  override fun findBySku(sku: Long): Product {


    val localFile = ClassPathResource(localStorage)
    val json = localFile.file.readText(Charset.forName("UTF-8"))

    val products: List<Product?> = if(json.isNotBlank()) {
      mapper.readValue(json)
    } else {
      listOf()
    }

    return products.find { product -> product?.sku == sku }?:throw CustomNotFoundException("Sku not found")
  }

  override fun findAll(): List<Product?> {


    val localFile = ClassPathResource(localStorage)
    val json = localFile.file.readText(Charset.forName("UTF-8"))

    val products: List<Product?> = if(json.isNotBlank()) {
      mapper.readValue(json)
    } else {
      listOf()
    }

    return products.sortedBy { it?.sku }
  }

  override fun delete(entity: Product) {


    val localFile = ClassPathResource(localStorage)
    val json = localFile.file.readText(Charset.forName("UTF-8"))

    val products: MutableList<Product?> = if(json.isNotBlank()) {
      mapper.readValue(json)
    } else {
      mutableListOf()
    }

    products.removeIf {product -> product?.sku == entity.sku}
    localFile.file.writeText(mapper.writeValueAsString(products))
  }

  override fun deleteAll() {


    val localFile = ClassPathResource(localStorage)
    localFile.file.writeText(mapper.writeValueAsString(emptyList<Product>()))
  }
}
