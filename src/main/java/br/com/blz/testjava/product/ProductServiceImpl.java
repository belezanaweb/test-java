package br.com.blz.testjava.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.commons.exception.InternalServerErrorException;
import br.com.blz.testjava.commons.exception.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product findBySku(Long sku) {
		
		Product product = this.productRepository.findBySku(sku).orElseThrow(NotFoundException::new);
		long quantity = product.getInventory().getWarehouses().stream().mapToLong(Warehouse::getQuantity).sum();
		product.getInventory().setQuantity(quantity);
		product.setIsMarketable(quantity > 0);
		return product;
	}
	
	@Override
	public Product save(Product post) {
		return this.productRepository.save(post).orElseThrow(InternalServerErrorException::new);
	}

	@Override
	public void delete(Long id) {
		this.productRepository.deleteBySku(id);
	}

}
