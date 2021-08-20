package br.com.blz.testjava.adapter_out.repository.product;

import br.com.blz.testjava.adapter_in.exception.custom_exception.DuplicateRecordException;
import br.com.blz.testjava.adapter_in.exception.custom_exception.ResultNotFoundException;
import br.com.blz.testjava.adapter_out.entity.ProductEntity;
import br.com.blz.testjava.adapter_out.mapper.IProductOutMapper;
import br.com.blz.testjava.application.domain.Product;
import br.com.blz.testjava.application.port.out.IProductRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ProductRepository implements IProductRepository {

    private Set<ProductEntity> productMockDb = new HashSet<>();

    private final IProductOutMapper productMapper;

    public ProductRepository(IProductOutMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);

        boolean exiteRegistro = productMockDb.stream()
            .filter(productDbMock -> product.getSku().equals(productEntity.getSku()))
            .findAny().isPresent();
        if (exiteRegistro){
            throw new DuplicateRecordException();
        }
        productMockDb.add(productEntity);

        return productMapper.toDomain(productEntity);
    }

    @Override
    public Product updateProduct(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        deleteProduct(productEntity.getSku());
        productMockDb.add(productEntity);
        return productMapper.toDomain(productEntity);
    }

    @Override
    public Product findProduct(Long sku) {
        return productMapper.toDomain( findBySku(sku) ) ;
    }

    @Override
    public void deleteProduct(Long sku) {
        productMockDb.remove(findBySku(sku));


    }

    private ProductEntity findBySku(Long sku){
        return productMockDb
            .stream().filter(productDbMock -> productDbMock.getSku().equals(sku)).findFirst()
            .orElseThrow( () -> new ResultNotFoundException() );

    }
}
