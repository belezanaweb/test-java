package br.com.blz.testjava.testjava.controller

import br.com.blz.testjava.testjava.model.Product
import br.com.blz.testjava.testjava.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RestController
@RequestMapping("/produto")
class ProductController {


    @Autowired
    private ProductService productService;


    /**
     * @param id
     * @return List<Product> .
     * @see Product
     */
    @GetMapping("/{sku}")
    ResponseEntity<Product> find(@PathVariable("sku") Long sku) {
        return new ResponseEntity<>(productService.findById(sku), HttpStatus.OK);
    }


    /**
     * @param ProductProduct
     * @return Product
     * @see Product
     */
    @PostMapping("")
    ResponseEntity<Product> save(@RequestBody @Valid final Product product) {

        println("Saving " + product.toString());

        productService.save(product);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * @param Product
     * @return List<IdDTO> .
     * @see Product
     */
    @PutMapping("")
    ResponseEntity<Product> update(@RequestBody @Valid final Product product) {

        println("Updating " + product.toString());

        productService.update(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @param sku
     */
    @DeleteMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    void delete(@PathVariable("sku") Long sku) {
        println("Deleting Product with SKU = " + sku + "...");
        productService.deleteById(sku);
    }

}
