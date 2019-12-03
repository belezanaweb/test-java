package br.com.blz.testjava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exceptions.ProductBadRequestException;
import br.com.blz.testjava.exceptions.ProductNotExistsException;
import br.com.blz.testjava.exceptions.ProductSkuAlreadyExistsException;
import br.com.blz.testjava.mappers.ProductMapper;
import br.com.blz.testjava.mappers.dtos.ProductDTO;
import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.models.Warehouse;
import br.com.blz.testjava.repostiories.ProductRepository;

@Service
@Qualifier("productService")
public class ProductService implements ServiceCRUD<ProductDTO, Product> {

	ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public Product create(ProductDTO productDTO) {
		if (productDTO.getSku() != null && productRepository.existsById(productDTO.getSku()))
			throw new ProductSkuAlreadyExistsException();

		Product product = productRepository.save(ProductMapper.toModel(productDTO));

		return product;

	}
	
	@Override
	public void update(Long sku, ProductDTO productDTO) {
		if (!sku.equals(productDTO.getSku())) {
			throw new ProductBadRequestException();
		}

		if (!productRepository.existsById(productDTO.getSku())) {
			throw new ProductNotExistsException();
		}

		productRepository.save(ProductMapper.toModel(productDTO));

	}
	
	@Override
	public void destroy(Long sku) {
		if (!productRepository.existsById(sku))
			throw new ProductNotExistsException();

		productRepository.deleteById(sku);
	}

	@Override
	public Product search(Long sku) {
	
		Product product = productRepository.findById(sku).orElseThrow(() -> new ProductNotExistsException());
		
		Integer quantity = product.getInventory().getWarehouses().stream().mapToInt(Warehouse::getQuantity).sum();

		product.getInventory().setQuantity(quantity);

		product.setMarketable(quantity > 0);

		return product;

	}
}
