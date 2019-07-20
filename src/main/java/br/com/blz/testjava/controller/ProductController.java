package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.api.request.CreateProductRequest;
import br.com.blz.testjava.domain.api.request.ReplaceProductRequest;
import br.com.blz.testjava.domain.api.request.WarehouseRequest;
import br.com.blz.testjava.domain.api.response.CreateProductResponse;
import br.com.blz.testjava.domain.api.response.InventoryResponse;
import br.com.blz.testjava.domain.api.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping
    public ResponseEntity<CreateProductResponse> create(@RequestBody @Valid CreateProductRequest request) {
        CreateProductResponse response = new CreateProductResponse();
        response.setSku(request.getSku());

        return ResponseEntity.created(getLocation(request)).body(response);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductResponse> read(@PathVariable("sku") String sku) {
        return ResponseEntity.ok(mock(sku));
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProductResponse> replace(@PathVariable("sku") String sku,
                                                   @RequestBody @Valid ReplaceProductRequest request) {
        return ResponseEntity.ok(mock(sku, request));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> delete(@PathVariable("sku") String sku) {
        return ResponseEntity.noContent().build();
    }

    private ProductResponse mock(String sku) {
        ReplaceProductRequest requestMock = new ReplaceProductRequest();

        requestMock.setName("Nome qualquer");

        ArrayList<WarehouseRequest> warehouses = new ArrayList<>();
        WarehouseRequest warehouse = new WarehouseRequest();
        warehouse.setLocality("SP");
        warehouse.setQuantity(10L);
        warehouse.setType("ECOMMERCE");
        warehouses.add(warehouse);
        requestMock.setWarehouses(warehouses);


        return mock(sku, requestMock);
    }

    private ProductResponse mock(String sku, ReplaceProductRequest request) {
        ProductResponse productResponse = new ProductResponse();

        productResponse.setSku(Long.valueOf(sku));
        productResponse.setName(request.getName());
        productResponse.setMarketable(true);

        InventoryResponse inventory = new InventoryResponse();
        inventory.setQuantity(10L);
        inventory.setWarehouses(request.getWarehouses());

        productResponse.setInventory(inventory);

        return productResponse;
    }

    private URI getLocation(@RequestBody @Valid CreateProductRequest request) {
        try {
            return new URI("/product/" + request.getSku());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
