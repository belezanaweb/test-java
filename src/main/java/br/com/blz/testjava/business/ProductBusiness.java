package br.com.blz.testjava.business;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.business.exception.ProductAlreadyExistsException;
import br.com.blz.testjava.business.model.ProductInventoryBO;
import br.com.blz.testjava.business.model.WharehouseBO;
import br.com.blz.testjava.business.model.impl.ProductInventoryBOImpl;
import br.com.blz.testjava.enumeration.ValidationMessageEnum;
import br.com.blz.testjava.enumeration.WharehouseTypeEnum;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Wharehouse;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.repository.WharehouseRepository;

@Service
public class ProductBusiness {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private WharehouseRepository wharehouseRepository;
	
	/**
	 * Cria uma base de dados inicial
	 */
	@PostConstruct
	public void init() {		
		for (Wharehouse wharehouse : wharehouseRepository.findAll()) {
			boolean intercalation = true;
			for (Product product : productRepository.findAll()) {
				if (intercalation) 
					wharehouse.addProduct(product);
				
				intercalation = !intercalation;
			}
		}	
	}
	
	/**
	 * Busca um produto em função do sku
	 * @param sku
	 * @return Produto
	 */
	public ProductInventoryBO find(Long sku) {
		Product foundProduct = productRepository.findById(sku);
		
		if (foundProduct != null) {
			List<Wharehouse> wharehouses = wharehouseRepository.findByProduct(foundProduct);
			return new ProductInventoryBOImpl(foundProduct, wharehouses);
		}
		
		return null;
	}

	/**
	 * Cria um novo produto no sistema
	 * @param product
	 */
	public void create(ProductInventoryBO product) {
	
		Product foundProduct = productRepository.findById(product.getSku());
		
		if (foundProduct != null) {
			throw new ProductAlreadyExistsException(ValidationMessageEnum.PRODUCT_ALREADY_EXISTS.getKey());
		}
		
		Product productEntity = getProductEntityFromBO(product);
		this.productRepository.create(getProductEntityFromBO(product));
		
		product.getInventory().getWarehouses().forEach(ws -> {
			Wharehouse foundWharehouse = this.wharehouseRepository.findById(ws.getLocality());
			
			if (foundWharehouse != null) {
				foundWharehouse.addProduct(productEntity, ws.getQuantity());
			} else {
				this.wharehouseRepository.create(getWharehouseEntityFromBO(ws, productEntity));
			}
		});
		
	}
	
	private Product getProductEntityFromBO(ProductInventoryBO product) {
		return new Product(product.getSku(), product.getName());
	}
	
	private Wharehouse getWharehouseEntityFromBO(WharehouseBO wharehouse, Product product) {		
		Wharehouse ws = new Wharehouse(wharehouse.getLocality(), WharehouseTypeEnum.valueOf(wharehouse.getType()));
		ws.addProduct(product, wharehouse.getQuantity());		
		return ws;
	}

	public void delete(Long sku) {

		Product foundProduct = productRepository.findById(sku);
		
		if (foundProduct != null) {
			this.wharehouseRepository.deleteAllProducts(foundProduct);			
			this.productRepository.delete(foundProduct);
		}		
		
	}

	public void update(ProductInventoryBO product) {

		Product foundProduct = productRepository.findById(product.getSku());
		if (foundProduct != null) {
			foundProduct.setName(product.getName());
			this.productRepository.update(foundProduct);
			
			product.getInventory().getWarehouses().forEach(ws -> {				
				Wharehouse foundWharehouse = this.wharehouseRepository.findById(ws.getLocality());
				foundWharehouse.changeProduct(foundProduct, ws.getQuantity());
				
				Wharehouse wharehouse = new Wharehouse(ws.getLocality(),WharehouseTypeEnum.valueOf(ws.getType()));
				wharehouse.addAllProducts(foundWharehouse.getProducts());				
				this.wharehouseRepository.update(wharehouse);
			});
			
		}		
		
	}	
		
}
