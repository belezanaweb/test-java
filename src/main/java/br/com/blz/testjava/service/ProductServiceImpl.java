package br.com.blz.testjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dao.ProductRepository;
import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductServiceImpl() {
		super();
	}

	@Override
	public void save(Product obj) {
		// TODO Auto-generated method stub
		if (validateInsert(obj)) {
			productRepository.save(obj);
		} else {
			throw new BusinessException("Could not register.");
		}
	}

	@Override
	public void update(Product obj) {
		// TODO Auto-generated method stub
		if (validate(obj)) {
			productRepository.save(obj);
		} else {
			throw new BusinessException("Could not register.");
		}
	}

	@Override
	public void remove(Product obj) {
		productRepository.delete(obj);
	}

	@Override
	public boolean validate(Product obj) {
		// TODO Auto-generated method stub
		if (obj.getSku() <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public void editBySku(Product dto) {
		// TODO Auto-generated method stub
		Optional<Product> optProduct = productRepository.findBySku(dto.getSku());
		if (optProduct.isPresent()) {				
			dto.setId(optProduct.get().getId());
			dto.getInventory().setId(optProduct.get().getInventory().getId());
			this.update(dto);
		} else {
			throw new ProductNotFoundException("Product not found.");
		}
	}

	@Override
	public Product findBySku(long sku) {
		// TODO Auto-generated method stub
		Optional<Product> optProduct = productRepository.findBySku(sku);
		if (optProduct.isPresent()) {
			return optProduct.get();
		} else {
			throw new ProductNotFoundException("Product not found.");
		}
	}

	@Override
	public void deleteBySku(long sku) {
		// TODO Auto-generated method stub
		Optional<Product> optProduct = productRepository.findBySku(sku);
		if (optProduct.isPresent()) {
			this.removeById(optProduct.get().getId());
		} else {
			throw new ProductNotFoundException("Product not found.");
		}
	}

	@Override
	public boolean validateInsert(Product obj) {
		// TODO Auto-generated method stub
		if (validate(obj)) {
			if (productRepository.findBySku(obj.getSku()).isPresent()) {
				return false;
			}

			if (obj.getInventory() != null) {
				obj.getInventory().setProduct(obj);
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Product> listAll() {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public void removeById(long id) {
		// TODO Auto-generated method stub
		productRepository.delete(id);
	}

	@Override
	public Product findById(long id) {
		// TODO Auto-generated method stub
		return productRepository.findOne(id);
	}

}
