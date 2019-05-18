package br.com.blz.testjava.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exceptionhandler.exception.ProductAlreadyExistException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	private MessageSource messageSource;

	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,
								MessageSource messageSource) {
		this.productRepository = productRepository;
		this.messageSource = messageSource;
	}

	@Override
	public void save(Product entity) {
		
		if(this.productRepository.existsById(entity.getSku())) {
			throw new ProductAlreadyExistException(this.messageSource.getMessage("product.same-sku", null, LocaleContextHolder.getLocale()));
		}
		
		 this.productRepository.save(entity);
	}

	@Override
	public Product update(Integer id, Product entity) {
		Product product = this.findById(id);
		
		BeanUtils.copyProperties(entity, product);
				
	    return this.productRepository.save(product);
	}

	@Override
	public Product findById(Integer id) {
		Optional<Product> item = this.productRepository.findById(id);

		return item.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	@Override
	public Iterable<Product> findAll() {
		return this.productRepository.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		this.productRepository.deleteById(id);
	}
	

}
