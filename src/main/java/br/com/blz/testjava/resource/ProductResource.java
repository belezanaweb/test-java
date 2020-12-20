package br.com.blz.testjava.resource;

import br.com.blz.testjava.model.entity.Product;
import br.com.blz.testjava.service.ProductService;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class ProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    /**
     * {@code POST} : Create a new product.
     *
     * @param product the product to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new
     * product, or with status {@code 400 (Bad Request)} if the product sku already exists.
     */
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody @Valid Product product) {
        try {
            log.debug("REST request to save Product : {}", product);
            Product result = productService.save(product);
            return ResponseEntity.created(new URI("/api/products/" + product.getSku()))
                .body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    /**
     * {@code PUT} : Updates an existing product.
     *
     * @param product the product to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated
     * product, or with status {@code 400 (Bad Request)} if the product sku does not exists.
     */
    @PutMapping
    public ResponseEntity<Product> update(@RequestBody @Valid Product product) {
        try {
            log.debug("REST request to update Product : {}", product);
            Product result = productService.update(product);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    /**
     * {@code GET} : get all the products.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of products in
     * body.
     */
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        log.debug("REST request to get all Products");
        List<Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    /**
     * {@code GET  /:sku} : get product by sku.
     *
     * @param sku the sku of the product to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the product, or
     * with status {@code 404 (Not Found)}.
     */

    @GetMapping("/{sku}")
    public ResponseEntity<Product> find(@PathVariable Long sku) {
        log.debug("REST request to get Product : {}", sku);
        Optional<Product> product = productService.find(sku);
        return product.map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * {@code DELETE  /:sku} : delete product by sku.
     *
     * @param sku the sku of the product to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable Long sku) {
        log.debug("REST request to delete Product : {}", sku);
        Boolean deleted = productService.delete(sku);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Handle bean validation messages.
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;

    }
}
