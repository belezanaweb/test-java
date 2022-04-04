package br.com.blz.testjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.api.v1.model.ProductDTO;
import br.com.blz.testjava.converter.IProductConverter;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.exception.DuplicateItemException;
import br.com.blz.testjava.exception.NotFoundException;
import br.com.blz.testjava.repository.IProductRepository;
import br.com.blz.testjava.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private IProductConverter productConverter;
	
	@Override
	public ProductDTO findOne(final Long sku) {
		this.checkIfExists(sku);
		
		return this.productConverter
				.toDTO(this.productRepository.findOne(sku));
	}

	@Override
	public ProductDTO create(final ProductDTO dto) {
		this.checkDuplicateItem(dto.getSku());
		
		final Product product = this.productConverter.toDomain(dto);
		final Product newProduct =this.productRepository.create(product);
		return this.productConverter.toDTO(newProduct);
	}

	@Override
	public void delete(final Long sku) {
		this.checkIfExists(sku);
		
		this.productRepository.delete(sku);
	}

	@Override
	public ProductDTO update(final Long sku, final ProductDTO dto) {
		this.checkIfExists(sku);
		if(!sku.equals(dto.getSku())) 
			throw new BusinessException(String
					.format("O sku do produto (%s) é diferente do sku informado para alteração (%s)", dto.getSku(), sku));
		
		final Product productToUpdate = this.productConverter.toDomain(dto);
		return this.productConverter
				.toDTO(this.productRepository.update(sku, productToUpdate));
	}
	
	private void checkIfExists(final Long sku) {
		if(!this.productRepository.existsBySku(sku)) 
			throw new NotFoundException(String.format("Produto não encontrado com o sku: %s", sku));
	}
	
	private void checkDuplicateItem(final Long sku) {
		if(this.productRepository.existsBySku(sku)) 
			throw new DuplicateItemException(
					String.format("Erro ao adicionar o produto. Já existe um produto cadastrado com o sku: %s.", sku));
	}

	@Override
	public void deleteAll() {
		this.productRepository.deleteAll();
	}
}