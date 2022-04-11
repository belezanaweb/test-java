package br.com.blz.testjava

import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.responses.InventoryResponse
import br.com.blz.testjava.model.responses.ProductResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*
import org.springframework.web.server.ResponseStatusException

@RestController
class ProductController(val dao: ProductDao = ProductDao()) {

  @GetMapping("/")
  fun home(): ResponseEntity<Void> {
    return ResponseEntity<Void>(HttpStatus.OK)
  }

  @RequestMapping(value = ["/products"], method = [POST],
                  consumes = ["application/json; charset=utf-8"])
  fun create(@RequestBody product: Product): ResponseEntity<Void> {
    return if (dao.get(product.sku) == null) {
      dao.create(product)
      ResponseEntity<Void>(HttpStatus.CREATED)
    }
    else {
      throw ResponseStatusException(HttpStatus.CONFLICT, "Product already exists")
    }
  }

  @RequestMapping(value = ["/products/{sku}"], method = [GET], produces = ["application/json; charset=utf-8"])
  fun retrieve(@PathVariable(value = "sku") sku: Int): ResponseEntity<ProductResponse> {
    fun from(p: Product): ProductResponse {
      val onHands = p.inventory.warehouses.map { it.quantity }.sum()
      val inventory = InventoryResponse(onHands, p.inventory.warehouses)
      return ProductResponse(p.name, p.sku, inventory, onHands > 0)
    }
    val product = dao.get(sku)
    return if (product == null)
      ResponseEntity<ProductResponse>(HttpStatus.NOT_FOUND)
    else {
      ResponseEntity<ProductResponse>(from(product), HttpStatus.OK);
    }
  }

  @RequestMapping(value = ["/products/{sku}"], method = [PUT],
    consumes = ["application/json; charset=utf-8"])
  fun update(@PathVariable(value = "sku") sku: Int, @RequestBody product: Product): ResponseEntity<Void> {
    return if (dao.get(sku) == null) {
      ResponseEntity<Void>(HttpStatus.NOT_FOUND)
    }
    else {
      dao.update(product)
      ResponseEntity<Void>(HttpStatus.OK)
    }
  }

  @RequestMapping(value = ["/products/{sku}"], method = [DELETE])
  fun delete(@PathVariable(value = "sku") sku: Int): ResponseEntity<Void> {
    return if (dao.get(sku) == null) {
      ResponseEntity<Void>(HttpStatus.NOT_FOUND)
    }
    else {
      dao.delete(sku)
      ResponseEntity<Void>(HttpStatus.OK)
    }
  }

}
