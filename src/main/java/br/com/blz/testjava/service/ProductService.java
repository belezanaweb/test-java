package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.request.ProductCreateRequest;
import br.com.blz.testjava.database.ProductDatabase;
import br.com.blz.testjava.domain.request.ProductUpdateRequest;
import br.com.blz.testjava.domain.response.InventoryResponse;
import br.com.blz.testjava.domain.response.ProductResponse;
import br.com.blz.testjava.domain.response.WarehouseResponse;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.exception.UnprocessableEntityException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    @Autowired
    private ProductDatabase productDatabase;

    public ProductResponse createProduct(ProductCreateRequest productCreateRequest) {
        if (productDatabase.exists(productCreateRequest.getSku())) {
            throw new UnprocessableEntityException(
                String.format("Product sku [%d] already exists", productCreateRequest.getSku()));
        }

        return buildProductResponse(productDatabase.createProduct(new Product(productCreateRequest)));
    }

    public ProductResponse updateProduct(Long sku, ProductUpdateRequest productUpdateRequest) {
        if (!productDatabase.exists(sku)) {
            throw new UnprocessableEntityException(
                String.format("Product sku [%d] not exists", sku));
        }

        return buildProductResponse(productDatabase.updateProduct(new Product(sku, productUpdateRequest)));
    }

    public ProductResponse getProduct(Long sku) {
        Product product = productDatabase.getProduct(sku)
            .orElseThrow(() -> new NotFoundException(String.format("Product [%d] not found", sku)));
        return buildProductResponse(product);
    }

    public void deleteProduct(Long sku) {
        productDatabase.deleteProduct(sku);
    }

    private ProductResponse buildProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setSku(product.getSku());
        productResponse.setName(product.getName());
        productResponse.setInventory(buildInventoryResponse(product.getInventory()));
        return productResponse;
    }

    private InventoryResponse buildInventoryResponse(Inventory inventory) {
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setWarehouses(inventory
            .getWarehouses()
            .stream()
            .map(this::buildWarehouseResponse)
            .collect(toList()));

        return inventoryResponse;
    }

    private WarehouseResponse buildWarehouseResponse(Warehouse warehouse) {
        WarehouseResponse warehouseResponse = new WarehouseResponse();
        warehouseResponse.setLocality(warehouse.getLocality());
        warehouseResponse.setQuantity(warehouse.getQuantity());
        warehouseResponse.setType(warehouseResponse.getType());
        return warehouseResponse;
    }
}
