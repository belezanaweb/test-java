package br.com.blz.testjava.infrastracture.api.controllers;

import br.com.blz.testjava.application.product.InventoryInput;
import br.com.blz.testjava.application.product.create.CreateProductCommand;
import br.com.blz.testjava.application.product.create.CreateProductOutput;
import br.com.blz.testjava.application.product.create.CreateProductUseCase;
import br.com.blz.testjava.application.product.delete.DeleteProductUseCase;
import br.com.blz.testjava.application.product.retrieve.GetProductBySkuUseCase;
import br.com.blz.testjava.application.product.retrieve.ProductOutput;
import br.com.blz.testjava.application.product.update.UpdateProductCommand;
import br.com.blz.testjava.application.product.update.UpdateProductUseCase;
import br.com.blz.testjava.infrastracture.api.ProductAPI;
import br.com.blz.testjava.infrastracture.product.models.CreateProductRequest;
import br.com.blz.testjava.infrastracture.product.models.ProductResponse;
import br.com.blz.testjava.infrastracture.product.models.UpdateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class ProductController implements ProductAPI {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductBySkuUseCase getProductBySkuUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(final CreateProductUseCase createProductUseCase, final GetProductBySkuUseCase getProductBySkuUseCase, final UpdateProductUseCase updateProductUseCase, final DeleteProductUseCase deleteProductUseCase) {
        this.createProductUseCase = Objects.requireNonNull(createProductUseCase);
        this.getProductBySkuUseCase = Objects.requireNonNull(getProductBySkuUseCase);
        this.updateProductUseCase = Objects.requireNonNull(updateProductUseCase);
        this.deleteProductUseCase = Objects.requireNonNull(deleteProductUseCase);
    }

    @Override
    public ResponseEntity<?> createProduct(final CreateProductRequest input) {
        final var command = CreateProductCommand.with(input.sku(), input.name(), InventoryInput.with(input.inventory().warehouses()));
        CreateProductOutput output = this.createProductUseCase.execute(command);
        return ResponseEntity.created(URI.create("/products/" + output.sku())).body(output);
    }

    @Override
    public ProductResponse getBySku(final Long sku) {
        return ProductResponse.from(this.getProductBySkuUseCase.execute(sku));
    }

    @Override
    public ResponseEntity<?> updateBySku(final Long sku, final UpdateProductRequest input) {
        final var command = UpdateProductCommand.with(input.sku(), input.name(), InventoryInput.with(input.inventory().warehouses()));
        ProductOutput output = this.updateProductUseCase.execute(command);
        return ResponseEntity.ok().body(output);
    }

    @Override
    public void deleteBySku(final Long sku) {
        this.deleteProductUseCase.execute(sku);
    }
}
