package br.com.blz.testjava.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.commons.exception.InternalServerErrorException;
import br.com.blz.testjava.commons.exception.NotFoundException;
import br.com.blz.testjava.commons.exception.ProductAlreadyExistsException;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product findBySku(Long sku) {
		Product product = this.productRepository.findBySku(sku).orElseThrow(NotFoundException::new);
		long quantity = product.getInventory().getWarehousesInventoryQuantity();
		product.getInventory().setQuantity(quantity);
		product.setIsMarketable(quantity > 0);
		return product;
	}
	
	@Override
	public Boolean hasBySku(Long sku) {
		return this.productRepository.hasBySku(sku);
	}
	
	@Override
	public Product save(Product product) {

		if(this.productRepository.findBySku(product.getSku()).isPresent()) {
			throw new ProductAlreadyExistsException();
		}

		return this.productRepository.save(product).orElseThrow(InternalServerErrorException::new);
	}
	
	@Override
	public Product update(Product product) {

		if(!this.productRepository.findBySku(product.getSku()).isPresent()) {
			throw new NotFoundException();
		}

		return this.productRepository.save(product).orElseThrow(InternalServerErrorException::new);
	}

	@Override
	public void delete(Long id) {
		this.productRepository.deleteBySku(id);
	}

}
