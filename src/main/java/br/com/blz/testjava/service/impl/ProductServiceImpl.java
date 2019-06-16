/**
 * 
 */
package br.com.blz.testjava.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WareHouseDTO;
import br.com.blz.testjava.entity.ProductEntity;
import br.com.blz.testjava.entity.WareHouseEntity;
import br.com.blz.testjava.exception.ProductNotFoudException;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.repository.WareHouseRepository;
import br.com.blz.testjava.service.ProductService;

/**
 * @author Augusto Lemes
 * @since 16/06/2019
 *
 */
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	@Autowired
	private ProductFactory factory;
	
	public Boolean delete(Long sku) throws ProductNotFoudException {
		ProductEntity product = findBySku(sku);
		
		
		
		List<WareHouseEntity> lista = wareHouseRepository.findByProduct(product);
		for(WareHouseEntity w:lista) {
			wareHouseRepository.delete(w);
		}
		productRepository.delete(product);
		return Boolean.TRUE;
	}
	
	public void save(ProductDTO productDTO) {
		ProductEntity product = factory.parsing(productDTO);
		productRepository.save(product);
		
		for(WareHouseDTO dto:productDTO.getInventory().getWarehouses()) {
			WareHouseEntity entity = factory.parsing(dto, product);
			wareHouseRepository.save(entity);
		}

	}
	
	private ProductEntity findBySku(Long sku) throws ProductNotFoudException {
		ProductEntity product = productRepository.findOne(sku);
		if(product == null) {
			throw new ProductNotFoudException("Product not found.");
		}
		
		return product;
		
	}
	
	public ProductDTO findOne(Long sku) throws ProductNotFoudException{
		ProductEntity product = findBySku(sku);
		
		List<WareHouseEntity> lista = wareHouseRepository.findByProduct(product);
		ProductDTO dto = factory.parsing(lista, product);
		return dto;
	}
	
	@Component
	class ProductFactory{
		
		public ProductEntity parsing(ProductDTO dto) {
			ProductEntity entity = ProductEntity.builder().build();
			entity.setSku(dto.getSku());
			entity.setName(dto.getName());
			return entity;
		}
		
		public WareHouseEntity parsing(WareHouseDTO dto, ProductEntity product) {
			WareHouseEntity entity = WareHouseEntity.builder().build();
			entity.setLocality(dto.getLocality());
			entity.setQuantity(dto.getQuantity());
			entity.setType(dto.getType());
			entity.setProduct(product);
			return entity;
		}
		
		public ProductDTO parsing(List<WareHouseEntity> lista, ProductEntity product) {
			List<WareHouseDTO> listaDto = new ArrayList<>();
			for(WareHouseEntity e:lista) {
				WareHouseDTO dto = parsing(e);
				listaDto.add(dto);
			}
			InventoryDTO inventory = InventoryDTO.builder().build();
			ProductDTO dto = parsing(product);
			inventory.setWarehouses(listaDto);
			dto.setInventory(inventory);
			return dto;
		}
		
		public ProductDTO parsing(ProductEntity entity) {
			ProductDTO dto = ProductDTO.builder().build();
			dto.setName(entity.getName());
			dto.setSku(entity.getSku());
			return dto;
		}
		
		public WareHouseDTO parsing(WareHouseEntity entity) {
			WareHouseDTO dto = WareHouseDTO.builder().build();
			dto.setLocality(entity.getLocality());
			dto.setQuantity(entity.getQuantity());
			dto.setType(entity.getType());
			return dto;
		}
	}
}
