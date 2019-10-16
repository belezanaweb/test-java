package br.com.blz.testjava.resource;

import br.com.blz.testjava.business.ProductBusiness;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import br.com.blz.testjava.exception.SKUExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductBusiness productBusiness;

    @GetMapping
    public ResponseEntity list() {
        List<ProductDto> productsDto = productBusiness.list();
        return (!productsDto.isEmpty())
            ? ResponseEntity.status(HttpStatus.OK).body(productsDto)
            : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ProductDto productDto) throws SKUExistsException {
        ProductDto productSave = productBusiness.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productSave);
    }

    @GetMapping("/{sku}")
    public ResponseEntity findBySky(@PathVariable("sku") Long sku) throws ResourceNotFoundException {
        ProductDto productDto = productBusiness.findBySku(sku);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity delete(@PathVariable("sku") Long sku) throws ResourceNotFoundException {
        productBusiness.deleteBySku(sku);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ProductDto productDto) throws ResourceNotFoundException {
        ProductDto productUpdated = productBusiness.update(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productUpdated);
    }

}
