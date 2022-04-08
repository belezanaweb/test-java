package br.com.blz.testjava.product;

import br.com.blz.testjava.application.dto.product.ProductDto;
import br.com.blz.testjava.application.dto.product.ProductForm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product)  {
        Product productExiste = productRepository.findBySkuProduct(product.getSku())
            .orElse(null);
        if (productExiste != null) {
            throw new IllegalArgumentException("Ja existe produto cadastrado com o SKU: " + product.getSku());
        }
        return productRepository.saveProduct(product);
    }

    @Override
    public ProductDto findBySkuProduct(Long sku) {
        Product product = productRepository.findBySkuProduct(sku)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao ha produto cadastrado com o SKU: " + sku));

        return new ProductDto(product);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<ProductDto> productsDto = productRepository.findAllProducts()
            .stream()
            .map(prod ->{
                return new ProductDto(prod);
            }).collect(Collectors.toList());
        if(productsDto.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Nao ha produto cadastrado");
        }
        return productsDto;
    }

    @Override
    public Long updateProduct(Long sku, ProductForm productForm) throws NoSuchFieldException {
        Product productExiste = productRepository.findBySkuProduct(sku)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Nao ha produto cadastrado com o SKU: " + sku));
        productRepository.deleteProduct(sku);
        Product product = productForm.converte();
        productRepository.saveProduct(product);
        return product.getSku();
    }

    @Override
    public void deleteProduct(Long sku) {
        Product product = productRepository.findBySkuProduct(sku)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao ha produto cadastrado com o SKU: " + sku));
        productRepository.deleteProduct(sku);
    }
}
