package br.com.blz.testjava.api.resources;

import br.com.blz.testjava.api.dtos.ProductDTO;
import br.com.blz.testjava.api.exceptions.ApiErrors;
import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.model.entities.Inventory;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.entities.Warehouse;
import br.com.blz.testjava.services.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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

    private ProductService productService;
    private ModelMapper modelMapper;

    public ProductResource(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product = productService.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    @GetMapping("{sku}")
    public ProductDTO getProductBySku(@PathVariable Long sku) {
        return productService.getBySku(sku)
            .map(product -> modelMapper.map(product, ProductDTO.class))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{sku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long sku) {
        Product product = productService.getBySku(sku)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        productService.delete(product);
    }

    @PutMapping("{sku}")
    public ProductDTO update(@PathVariable Long sku, @RequestBody ProductDTO productDTO) {
        Product productToUpdate = modelMapper.map(productDTO, Product.class);

        return productService.getBySku(sku).map(product -> {
            List<Warehouse> warehouses = new ArrayList<>();

            productToUpdate.getInventory().getWarehouses().forEach(w -> {
                Warehouse warehouse = Warehouse.builder()
                    .id(w.getId())
                    .quantity(w.getQuantity())
                    .locality(w.getLocality())
                    .type(w.getType())
                    .build();
                warehouses.add(warehouse);
            });

            Inventory inventory = Inventory.builder()
                .id(productToUpdate.getInventory().getId()).warehouses(warehouses).build();

            warehouses.forEach(w -> w.setInventory(inventory));

            product.setSku(productToUpdate.getSku());
            product.setName(productToUpdate.getName());
            product.setInventory(inventory);
            product = productService.update(product);
            return modelMapper.map(product, ProductDTO.class);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrors handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return new ApiErrors(bindingResult);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ApiErrors handleBusinessException(BusinessException ex) {
        return new ApiErrors(ex);
    }

}
