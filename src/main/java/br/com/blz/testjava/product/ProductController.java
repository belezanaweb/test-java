package br.com.blz.testjava.product;

import br.com.blz.testjava.exception.NotFoundProductException;
import br.com.blz.testjava.exception.ProductSkuExistsException;
import br.com.blz.testjava.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.PRODUCT_PATH)
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(Constants.PATH_PARAM_SKU)
    public ProductEntity findBySku(@PathVariable String sku) throws NotFoundProductException {
        return productService.findBySku(sku);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated ProductEntity productEntity) throws ProductSkuExistsException {
        productService.save(productEntity);
    }

    @PutMapping(Constants.PATH_PARAM_SKU)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ProductEntity productEntity,
                       @PathVariable String sku) throws NotFoundProductException {
        productService.update(productEntity, sku);
    }

    @DeleteMapping(Constants.PATH_PARAM_SKU)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String sku){
        productService.delete(sku);
    }
}
