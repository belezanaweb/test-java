package br.com.blz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.domain.Product;
import br.com.blz.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductServiceImpl() {
		super();
	}

	@Override
	public void save(Product obj) throws Exception {
		
		if (productExist(obj)) {
			productRepository.save(obj);
		} else {
			throw new Exception("Não foi possivel cadastrar produto. Produto ja existe.");
		}
	}

	@Override
	public void update(Product prod) throws Exception {
		
		if (validate(prod)) {
			productRepository.save(prod);
		} else {
			throw new Exception("Não foi possível atualizar produto.");
		}
	}

	@Override
	public void delete(Product obj) {
		productRepository.delete(obj);
	}

	@Override
	public boolean validate(Product prod) {
		
		if (prod.getSku() <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public void updateBySku(Product prod) throws Exception {

		Optional<Product> optProduct = productRepository.findBySku(prod.getSku());
		
		if (optProduct.isPresent()) {	
			
			prod.setId(optProduct.get().getId());
			prod.getInventory().setId(optProduct.get().getInventory().getId());
			this.update(prod);
			
		} else {
			throw new Exception("Produto não encontrado!");
		}
	}

	@Override
	public Product findBySku(long sku) throws Exception {

		Optional<Product> optProduct = productRepository.findBySku(sku);
		
		if (optProduct.isPresent()) {
			return optProduct.get();
		} else {
			throw new Exception("Produto não encontrado.");
		}
	}

	@Override
	public void deleteBySku(long sku) throws Exception {
		
		Optional<Product> optProduct = productRepository.findBySku(sku);
		
		if (optProduct.isPresent()) {
			productRepository.deleteById(optProduct.get().getId());
		} else {
			throw new Exception("Não foi possivel excluir, produto não encontrado.");
		}
	}

	@Override
	public boolean productExist(Product prod) {
		
		if (validate(prod)) {
			//prod ja existe, então não continua
			if (productRepository.findBySku(prod.getSku()).isPresent()) {
				return false;
			}

			if (prod.getInventory() != null) {
				prod.getInventory().setProduct(prod);
			}

			return true;
		} else {
			return false;
		}
	}

}