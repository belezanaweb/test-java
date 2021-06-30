package br.com.blz.testjava.resource


import br.com.blz.testjava.dto.ProductDto
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.service.ProductService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.text.ParseException
import java.util.stream.Collectors
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@RestController
@RequestMapping("/products")
class ProductResource(private val service: ProductService) {

    @Autowired
    private val modelMapper: ModelMapper? = null

    @GetMapping
    fun findAll(): ResponseEntity<List<ProductDto>> {
        val products = service.findAll()
        val productsDto: List<ProductDto> = products
            .stream()
            .map(this::convertToDto)
            .collect(Collectors.toList())

        return ResponseEntity.ok(productsDto)
    }

    @GetMapping("/{sku}")
    fun findById(@PathVariable sku: Long): ResponseEntity<ProductDto> {

        val product: Product = service.findById(sku)
        val productDto = this.convertToDto(product)

        return ResponseEntity.ok(productDto)
    }

    @PostMapping
    fun create(@Valid @RequestBody productDto: ProductDto, response: HttpServletResponse): ResponseEntity<ProductDto> =
        try {
            var product: Product = this.convertToEntity(productDto)
            product = service.create(product)
            ResponseEntity.status(HttpStatus.CREATED).body(this.convertToDto(product))
        } catch (e: Error) {
            ResponseEntity.internalServerError().build()
        }

    @PutMapping("/{sku}")
    fun update(@PathVariable sku: Long, @Valid @RequestBody productDto: ProductDto): ResponseEntity<ProductDto> {
        var product: Product = this.convertToEntity(productDto)
        product.sku = sku
        product = service.update(product)

        return ResponseEntity.ok(this.convertToDto(product))
    }

    @DeleteMapping("/{sku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable sku: Long) =
        service.delete(sku)

    private fun convertToDto(product: Product): ProductDto =
        modelMapper!!.map(product, ProductDto::class.java)

    @Throws(ParseException::class)
    private fun convertToEntity(productDto: ProductDto): Product =
        modelMapper!!.map(productDto, Product::class.java)

}
