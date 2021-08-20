package br.com.blz.testjava.adapter_in.controller.v1;


import br.com.blz.testjava.adapter_in.mapper.IProductInMapper;
import br.com.blz.testjava.adapter_in.request.ProductRequest;
import br.com.blz.testjava.adapter_in.response.ProductResponse;
import br.com.blz.testjava.application.port.in.IUseCaseProduct;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProductController implements IProductController {

    private final IUseCaseProduct useCaseProduct;
    private final IProductInMapper productMapper;

    public ProductController(IUseCaseProduct useCaseProduct, IProductInMapper productMapper) {
        this.useCaseProduct = useCaseProduct;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse createProduct(@Valid ProductRequest productRequest) {
        return productMapper.toReponse(useCaseProduct.createProduct(productMapper.toDomain(productRequest)));
    }

    @Override
    public ProductResponse updateProduct(@Valid ProductRequest productRequest) {
        return productMapper.toReponse(useCaseProduct.updateProduct(productMapper.toDomain(productRequest)));
    }

    @Override
    public ProductResponse findProduct(Long sku) {
        return productMapper.toReponse(useCaseProduct.findProduct(sku));
    }

    @Override
    public void deleteProduct(Long sku) {
        useCaseProduct.deleteProduct(sku);
    }
}
