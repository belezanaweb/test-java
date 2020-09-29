package br.com.blz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.blz.dto.ProductDTO;
import br.com.blz.exception.BusinessException;
import br.com.blz.model.Product;
import br.com.blz.repository.IProductRepository;
import br.com.blz.repository.WarehouseRepository;

@Repository
public class ProductService  {
	
	
	@Autowired
	private IProductRepository  IproductRepository;
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@Transactional
	public ProductDTO findId(Long sku) throws Exception{
		if(!IproductRepository.existsById(sku)) {
			throw new BusinessException("SKU Não Encontrado!");
		}
		ProductDTO productDTO= IproductRepository.findByIdReturnDTO(sku);
		productDTO.getInventory().setWarehouses(warehouseRepository.findBySKU(sku));
		return productDTO;
	}

	@Transactional
	public List<Product> findAll() {
		return IproductRepository.findAll();
	}

	@Transactional
	public void save(Product product) throws Exception {
		 product.getWarehouses().stream().forEach(item-> item.setProduct(product));
		if(IproductRepository.existsById(product.getSku())) 
		{
			throw new BusinessException("SKU já cadastrado!");
		}
		IproductRepository.save(product);
	}
	
	@Transactional
	public void save(ProductDTO productDTO) throws Exception {
		Product product = new Product(productDTO.getName(),productDTO.getSku(),productDTO.getInventory().getWarehouses());
		product.getWarehouses().stream().forEach(item-> item.setProduct(product));
		if(IproductRepository.existsById(product.getSku())) 
		{
			throw new BusinessException("SKU já cadastrado!");
		}
		IproductRepository.save(product);
	}
	
	@Transactional
	public void edit(Product product) throws BusinessException {
		if(!IproductRepository.existsById(product.getSku())) 
		{
			throw new BusinessException("SKU Não Encontrado!");
		}
		product.getWarehouses().stream().forEach(item-> item.setProduct(product));
		IproductRepository.saveAndFlush(product);
	}
	
	@Transactional
	public void edit(ProductDTO productDTO) throws BusinessException {
		Product product = new Product(productDTO.getName(),productDTO.getSku(),productDTO.getInventory().getWarehouses());
		product.getWarehouses().stream().forEach(item-> item.setProduct(product));
		if(!IproductRepository.existsById(product.getSku())) 
		{
			throw new BusinessException("SKU Não Encontrado!");
		}
		product.getWarehouses().stream().forEach(item-> item.setProduct(product));
		IproductRepository.saveAndFlush(product);
	}
	
//	@Transactional
//	public void edit(ProductDTO productDTO) throws BusinessException {
//		Product product = new Product(productDTO.getName(),productDTO.getSku(),productDTO.getInventory().getWarehouses());
//		if(!IproductRepository.existsById(product.getSku())) 
//		{
//			throw new BusinessException("SKU Não Encontrado!");
//		}
//		product.getWarehouses().stream().forEach(item-> item.setProduct(product));
//		IproductRepository.saveAndFlush(product);
//	}

	public void deleteById(Long id) {
		IproductRepository.deleteById(id);
	}

}
