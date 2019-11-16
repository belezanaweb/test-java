package br.com.blz.testjava.service.impl;


import static java.util.stream.Collectors.toList;

import br.com.blz.testjava.controller.request.ProductRequest;
import br.com.blz.testjava.controller.request.WarehouseRequest;
import br.com.blz.testjava.controller.response.InventoryResponse;
import br.com.blz.testjava.controller.response.ProductResponse;
import br.com.blz.testjava.controller.response.WarehouseResponse;
import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.exception.UnprocessableEntityException;
import br.com.blz.testjava.repository.inmemory.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    
    private static final String PRODUCT_NOT_FOUND = "Product with sku [%d] not found!";
    private static final String PRODUCT_ALREADY_EXISTENT = "Product with sku [%d] already exists!";
    
    @Autowired
    private ProductRepository<Product> productRepository;
    
    @Override
    public ProductResponse findBySku(long sku) {
        Product product = productRepository.findBySku(sku)
            .orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_FOUND, sku)));
        
        return toResponse(product);
    }
    
    @Override
    public ProductResponse save(ProductRequest productRequest) {
        Product product = fromRequest(productRequest);
        Optional<Product> productRepo = productRepository.findBySku(product.getSku());
        
        if (productRepo.isPresent()) {
            throw new UnprocessableEntityException(
                String.format(PRODUCT_ALREADY_EXISTENT, product.getSku()));
        }
        
        return toResponse(productRepository.save(product));
    }
    
    @Override
    public ProductResponse update(ProductRequest productRequest) {
        Product product = fromRequest(productRequest);
        productRepository.findBySku(product.getSku())
            .orElseThrow(() -> new NotFoundException(
                String.format(PRODUCT_NOT_FOUND, product.getSku())));
        
        return toResponse(productRepository.save(product));
    }
    
    @Override
    public void remove(long sku) {
        if (!productRepository.remove(sku)) {
            throw new NotFoundException(String.format(PRODUCT_NOT_FOUND, sku));
        }
    }
    
    @Override
    public void clean() {
        productRepository.clean();
        
    }
    
    private ProductResponse toResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setSku(product.getSku());
        productResponse.setName(product.getName());
        productResponse.setInventory(new InventoryResponse(
            product.getInventory().getWarehouses().stream()
                .map(this::toWarehouseResponse)
                .collect(toList()),
            product.getInventory().getWarehouses().stream().mapToInt(Warehouse::getQuantity)
                .sum()));
        productResponse.setMarketable(productResponse.getInventory().getQuantity() > 0);
        
        return productResponse;
    }
    
    private WarehouseResponse toWarehouseResponse(Warehouse warehouse) {
        WarehouseResponse warehouseResponse = new WarehouseResponse();
        warehouseResponse.setLocality(warehouse.getLocality());
        warehouseResponse.setQuantity(warehouse.getQuantity());
        warehouseResponse.setType(warehouse.getType());
        
        return warehouseResponse;
    }
    
    private Product fromRequest(ProductRequest productRequest) {
        Product product = new Product();
        product.setSku(productRequest.getSku());
        product.setName(productRequest.getName());
        product.setInventory(new Inventory(
            productRequest.getInventory().getWarehouses().stream().map(this::fromWarehouseResponse)
                .collect(toList())));
        
        return product;
    }
    
    private Warehouse fromWarehouseResponse(WarehouseRequest warehouseRequest) {
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality(warehouseRequest.getLocality());
        warehouse.setQuantity(warehouseRequest.getQuantity());
        warehouse.setType(warehouseRequest.getType());
        
        return warehouse;
    }
    
}
