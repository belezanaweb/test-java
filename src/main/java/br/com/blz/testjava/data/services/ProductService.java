package br.com.blz.testjava.data.services;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.data.dto.ProductDTO;
import br.com.blz.testjava.data.dto.ProductListDTO;
import br.com.blz.testjava.domain.entities.Product;
import br.com.blz.testjava.domain.repositories.IProductRepository;

@Service
public class ProductService {
	
	private IProductRepository repository;
	private ModelMapper mapper;

	public ProductService(IProductRepository repository, ModelMapper mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public String add(ProductDTO product) throws Exception {
		
		
		if(this.productExists(product.getSku())) {
			throw new Exception("This product already exists");
		}
		
		Product productTOBeInserted = this.mapper.map(product, Product.class);
		String skuInserted = this.repository.add(productTOBeInserted);

		return skuInserted;
	}
	
	public String update(ProductDTO product ,String sku) {
		
		Product productTOBeUpdated = this.mapper.map(product, Product.class);
		Product productCurrent = this.repository.find(sku);
		
		if(productCurrent != null) {
			productCurrent = productTOBeUpdated;
			productCurrent.setSku(sku);
			this.repository.update(productCurrent, sku);
		}
		else {
			return ""; //returning empty string but could by a custom exception
		}

		return productCurrent.getSku();
	}
	
	public Boolean delete(String sku) {
		return this.repository.delete(sku);
	}
	
	public List<ProductListDTO> findAll() {
		List<Product> products = this.repository.findAll();
		
		List<ProductListDTO> productsListDTO = products
					.stream()
					.map(element -> this.mapper.map(element, ProductListDTO.class))
					.collect(Collectors.toList());
		return productsListDTO;
	}
	
	public ProductDTO find(String sku) {
		
		Product productFound = this.repository.find(sku);
		ProductDTO productDTOFound = null;
		
		if(productFound != null) {
			//one way to update the property isMarketable and inventory.quantity everytime a product is loaded
			productFound.setIsMarketable();
			productFound.getInventory().updateQuantity();
			productDTOFound = this.mapper.map(productFound, ProductDTO.class);
		}
		else {
			return null;
		}
		
		return productDTOFound;
	}
	
	
	private boolean productExists(String sku) {
		Product productFound = this.repository.find(sku);
		if(productFound != null) 
			return true;
		return false;
		
	}
	
	
}
