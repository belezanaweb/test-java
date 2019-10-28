package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dao.entity.Inventory;
import br.com.blz.testjava.dao.entity.Product;
import br.com.blz.testjava.dao.entity.ProductEntryPK;
import br.com.blz.testjava.dao.repository.IProductRepository;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.exception.ProductSKUMismatchException;

@Service
public class ProductService implements IProductService{

	@Autowired
    private IProductRepository productRepository;
	
	@Override
    public Iterable<Product> findAll() {
        Iterable<Product> products = productRepository.findAll();
        products.forEach(p -> setCalcFields(p));
        return products;
    }
    
	@Override
	public Product findById(Long sku) {
		Product product = productRepository.findById(buildProdSku(sku)).orElseThrow(ProductNotFoundException::new);
		setCalcFields(product);
		return product;
	}

	private void setCalcFields(Product product) {
		Inventory inventory = product.getInventory();
		Integer inventQtd = 0;
		if(inventory!=null) {
			inventQtd = calcInventQtd(inventory);
		}
		inventory.setQuantity(inventQtd);
		if(inventQtd>0){
			product.setIsMarketable(Boolean.TRUE);
		}else {
			product.setIsMarketable(Boolean.FALSE);
		}
	}

	private Integer calcInventQtd(Inventory inventory) {
		return inventory.getWarehouses().stream().mapToInt(p -> p.getQuantity()).sum();
	}

	@Override
    public Product create(Product product) {
    	if (null == product.getSku()){
            throw new IllegalArgumentException("Sku is requerid.");
    	}
    	
    	if (productRepository.findById(product.getSku()).isPresent()) {
            throw new IllegalArgumentException("Product Already Exists");
    	}
    	Product prod = productRepository.save(product);
    	setCalcFields(prod);
        return prod;
    }

	public static ProductEntryPK buildProdSku(Long sku) {
		ProductEntryPK productSku = new ProductEntryPK();
    	productSku.setSku(sku);
    	return productSku;
	}
    
	@Override
    public Product update(Long sku, Product product) {
    	if (null == product.getSku() || !product.getSku().getSku().equals(sku)) {
             throw new ProductSKUMismatchException("Product SKU mismatch.");
    	}
    	productRepository.findById(buildProdSku(sku))
        .orElseThrow(ProductNotFoundException::new);
       	Product prod = productRepository.save(product);
    	setCalcFields(prod);
        return prod;
    }

	@Override
    public void delete(Long sku) {
    	 productRepository.findById(buildProdSku(sku))
         .orElseThrow(ProductNotFoundException::new);
       productRepository.deleteById(buildProdSku(sku));
    }   
}