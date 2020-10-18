package br.com.blz.testjava.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.api.exceptions.ProductAlreadyExistsException;
import br.com.blz.testjava.api.exceptions.ProductNotExistsException;
import br.com.blz.testjava.model.InventoryItem;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.dto.InventoryItemDTO;
import br.com.blz.testjava.model.dto.InventoryItemsDTO;
import br.com.blz.testjava.model.dto.ProductDTO;
import br.com.blz.testjava.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public ProductDTO saveProduct(ProductDTO product) {
		log.info("saving product with SKU: {}", product.getSKU());
		if (this.checkProductIfexists(product.getSKU())) {
			throw new ProductAlreadyExistsException(
					String.format("Produto com id: %s já cadastrado na base de dados", product.getSKU())
					);
		}
		
		Product productEntity = dtoToEntity(product);
		this.repository.save(productEntity);
		
		return entityToDTO(productEntity);
	}
	
	public ProductDTO updateProduct(ProductDTO product) {
		log.info("updating product with SKU: {}", product.getSKU());
		if (!this.checkProductIfexists(product.getSKU())) {
			throw new ProductNotExistsException(
					String.format("Produto com id: %s não existe na base de dados", product.getSKU())
					);
		}
		
		Product productEntity = dtoToEntity(product);
		this.repository.save(productEntity);
		
		return entityToDTO(productEntity);
	}
	
	
	private boolean checkProductIfexists(String id) {
		return this.repository.existsById(id);
		
	}
	
	public List<ProductDTO>  getAll() {
		log.info("get all products called");
		List<ProductDTO> dto = this.repository.findAll().stream().map(product -> {
			return this.entityToDTO(product);
		}).collect(Collectors.toList());
		
		return dto;
	}

	public ProductDTO getById(String idProduct) {
		log.info("retrieving product with id {} ", idProduct);
		Product product = this.repository.findById(idProduct)
				.orElseThrow(() ->
					new ProductNotExistsException(
							String.format("Produto com id: %s não existe na base de dados", idProduct))
		);
		return entityToDTO(product);
	}

	public void deleteById(String idProduct) {
		log.info("deleting product with id {} ", idProduct);
		Product product = this.repository.findById(idProduct)
				.orElseThrow(() ->
					new ProductNotExistsException(
							String.format("Produto com id: %s não existe na base de dados", idProduct))
		);
		this.repository.deleteById(product.getSKU());
	}	

	public Product dtoToEntity(ProductDTO product) {
		Product productEntity = mapper.map(product, Product.class);
		if (Objects.nonNull(product.getInventory().getItems())) {
			Type listType = new TypeToken<List<InventoryItem>>(){}.getType();
			List<InventoryItem> items = mapper.map(product.getInventory().getItems(), listType);
			productEntity.setInventory(items);
		}	
		
		return productEntity;
	}
	
	public ProductDTO entityToDTO(Product product) {
		ProductDTO productDTO = mapper.map(product, ProductDTO.class);
		if (Objects.nonNull(product.getInventory()) && !product.getInventory().isEmpty()) {
			Long quantity = product.getInventory()
					.stream()
					.mapToLong(InventoryItem::getQuantity)
					.sum();
			
			InventoryItemsDTO inventory = new InventoryItemsDTO();
			
			List<InventoryItemDTO> items = product.getInventory().stream().map(item -> {
				InventoryItemDTO i = new InventoryItemDTO();
				i.setLocality(item.getLocality());
				i.setQuantity(item.getQuantity());
				i.setType(item.getType());
				return i;
			}).collect(Collectors.toList());
			
			inventory.setQuantity(quantity);
			
			productDTO.setMarketable(quantity > 0);
			
			inventory.setItems(items);
			
			productDTO.setInventory(inventory);
		}
		return productDTO;
	}
	
}
