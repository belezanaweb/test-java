package br.com.blz.testjava.business;

import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.dto.WarehouseDto;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import br.com.blz.testjava.exception.SKUExistsException;
import br.com.blz.testjava.mapper.ProductMapper;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductBusiness {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    public ProductDto save(ProductDto productDto) throws SKUExistsException {
        if(productService.findBySku(productDto.getSku()).isPresent()) throw new SKUExistsException();
        return this.saveAndGetProductDto(productDto);
    }

    public List<ProductDto> list() {
        List<Product> products = productService.list();
        return products.stream().map(product -> productMapper.ormToDto(product)).collect(Collectors.toList());
    }

    public ProductDto findBySku(Long sku) throws ResourceNotFoundException {
        Optional<Product> productOpt = productService.findBySku(sku);
        if (!productOpt.isPresent()) throw new ResourceNotFoundException();
        return this.convertAndProcessToProductDto(productOpt.get());
    }

    public void deleteBySku(Long sku) throws ResourceNotFoundException {
        Optional<Product> productOpt = productService.findBySku(sku);
        if (!productOpt.isPresent()) throw new ResourceNotFoundException();
        productService.delete(productOpt.get());
    }

    public ProductDto update(ProductDto productDto) throws ResourceNotFoundException {
        if(!productService.findBySku(productDto.getSku()).isPresent()) throw new ResourceNotFoundException();
        return this.saveAndGetProductDto(productDto);
    }

    private Integer calcQuantityProduct(ProductDto productDto) {
        List<WarehouseDto> warehouses = productDto.getInventory().getWarehouses();
        return warehouses.stream().mapToInt(WarehouseDto::getQuantity).sum();
    }

    private Boolean isMarketable(ProductDto productDto) {
        return productDto.getInventory().getQuantity() != null && productDto.getInventory().getQuantity() > 0;
    }

    private ProductDto saveAndGetProductDto(ProductDto productDto) {
        Product product = productMapper.dtoToOrm(productDto);
        Product productSave = productService.save(product);
        return this.convertAndProcessToProductDto(productSave);
    }

    private ProductDto convertAndProcessToProductDto(Product product) {
        ProductDto productDto = productMapper.ormToDto(product);
        productDto.getInventory().setQuantity(this.calcQuantityProduct(productDto));
        productDto.setIsMarketable(this.isMarketable(productDto));
        return productDto;
    }

}
