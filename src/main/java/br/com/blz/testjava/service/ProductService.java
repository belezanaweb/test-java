package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.converter.ProductConverter;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.repository.ProductRepositoryImpl;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductConverter converter;
    
    public ProductService() {
        this.repository = ProductRepositoryImpl.getInstance();
    }

    public ProductDTO getBySku(Long sku) {
        Product product = repository.getBySku(sku).map(p -> calcQuantityAndMarketable(p)).orElse(null);   
        return converter.toDto(product);
    }

    private Product calcQuantityAndMarketable(Product product) {
        Integer quantity = product.getInventory().getWarehouses().stream().mapToInt(Warehouse::getQuantity).sum();        
        product.getInventory().setQuantity(quantity);
        if(quantity.compareTo(0) > 0)
            product.setMarketable(true);
        return product;
    }

    public ProductDTO save(ProductDTO dto) throws BusinessException {
        
        repository.getBySku(dto.getSku()).ifPresent(p -> {
            throw new BusinessException("Dois produtos sao considerados iguais se os seus skus forem iguais");
        });              

        Product product = converter.toEntity(dto);
        product = repository.save(product);
        product = calcQuantityAndMarketable(product); 
        return converter.toDto(product);
    }

    public ProductDTO update(ProductDTO dto, Long sku) throws BusinessException {
        Product product = repository.getBySku(sku).orElseThrow(() -> new BusinessException("Produto nao encontrado"));
        product = converter.toEntity(dto);
        product = repository.update(product);
        product = calcQuantityAndMarketable(product); 
        return converter.toDto(product);
    }

    public void delete(Long sku) {
        boolean deleted = repository.delete(sku);
        
        if(!deleted)
            throw new BusinessException("Produto nao encontrado");
    }

}
