package br.com.blz.testjava.service;

import java.beans.FeatureDescriptor;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.ProductBadRequestException;
import br.com.blz.testjava.exception.ProductNotExistsException;
import br.com.blz.testjava.exception.ProductSkuAlreadyExistsException;
import br.com.blz.testjava.mapper.ProductMapper;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.dto.ProductDTO;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public Product create(ProductDTO productDto) {
		 if(productDto.getSku() != null) {			 
			 if(productRepository.existsById(productDto.getSku())) {
				 throw new ProductSkuAlreadyExistsException();
			 } 
		 }
			 
		 Product product = productRepository.save(ProductMapper.toEntity(productDto));
		 
		 return product;

	}

	public void updateProduct(Long sku, Product product) { 
		if(sku != product.getSku()) {
			throw new ProductBadRequestException();
		}
		
		if (productRepository.existsById(product.getSku())) {
			throw new ProductNotExistsException();
		}   

		productRepository.save(product);		
	}
	
	public Product searchProduct(Long sku) {
		Integer quantity = 0;
		Optional<Product> product = productRepository.findById(sku);

        if (!product.isPresent())
            throw new ProductNotExistsException();

        for (Warehouse warehouse : product.get().getInventory().getWarehouses()) {
        	quantity += warehouse.getQuantity();  
		}

        product.get().getInventory().setQuantity(quantity);

        product.get().setMarketable(quantity > 0);
	 
		return product.get();		
		
	}

}
