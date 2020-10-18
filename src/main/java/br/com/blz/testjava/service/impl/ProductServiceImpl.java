package br.com.blz.testjava.service.impl;

import br.com.blz.testjava.exceptionhandler.exception.ProductAlreadyExistException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<Product> product = this.productRepository.findBySku(entity.getSku());

        if(product.isPresent()) {
            throw  new ProductAlreadyExistException(this.messageSource.getMessage("product.same-sku", null, LocaleContextHolder.getLocale()));
        }

		 this.productRepository.save(entity);
	}

	@Override
	public Product updateBySku(Long sku, Product entity) {
        Product product = this.findBySku(sku);

        BeanUtils.copyProperties(entity, product, "id","sku", "inventory");
        product.setInventory(entity.getInventory());
        product.getInventory().setWarehouses(entity.getInventory().getWarehouses());

        return this.productRepository.save(product);
	}

	@Override
	public Product findBySku(Long sku) {
		Optional<Product> item = this.productRepository.findBySku(sku);

		return item.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	@Override
	public Iterable<Product> findAll() {
		return this.productRepository.findAll();
	}

	@Override
	public void deleteBySku(Long sku) {
		Product product = this.findBySku(sku);

	    this.productRepository.deleteById(product.getId());
	}
}
