package br.com.blz.testjava.infrastracture.configuration;

import br.com.blz.testjava.application.product.create.CreateProductUseCase;
import br.com.blz.testjava.application.product.create.DefaultCreateProductUseCase;
import br.com.blz.testjava.application.product.delete.DefaultDeleteProductUseCase;
import br.com.blz.testjava.application.product.delete.DeleteProductUseCase;
import br.com.blz.testjava.application.product.retrieve.DefaultGetProductBySkuUseCase;
import br.com.blz.testjava.application.product.retrieve.GetProductBySkuUseCase;
import br.com.blz.testjava.application.product.update.DefaultUpdateProductUseCase;
import br.com.blz.testjava.application.product.update.UpdateProductUseCase;
import br.com.blz.testjava.domain.product.ProductGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductUseCaseConfig {

    private final ProductGateway productGateway;

    public ProductUseCaseConfig(final ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Bean
    public CreateProductUseCase createProductUseCase() {
        return new DefaultCreateProductUseCase(productGateway);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase() {
        return new DefaultUpdateProductUseCase(productGateway);
    }

    @Bean
    public GetProductBySkuUseCase getProductByIdUseCase() {
        return new DefaultGetProductBySkuUseCase(productGateway);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase() {
        return new DefaultDeleteProductUseCase(productGateway);
    }
}
