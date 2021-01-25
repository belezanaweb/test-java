package br.com.blz.testjava.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.WareHouse;
import br.com.blz.testjava.repository.IProductRepository;

@Service
public class ProductService {

	private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private IProductRepository productRepository;

	public Product create(Product product) {

		LOG.info("Validação de SKU já existente");
		validExistProduct(product);

		LOG.info("Gravando o Produto");
		return productRepository.save(product);

	}

	private void validExistProduct(Product product) {
		if (productRepository.existsById(product.getSku())) {
			LOG.debug("Produto já cadastrado", product.getSku());
			throw new ProductDuplicateException(product.getSku());
		}
	}

	public Product find(Long sku) {
		Product db = productRepository.findById(sku).orElseThrow(() -> new ProductNotFound(sku));
		
		db.getInventory().setQuantity( calcularQuantity( db.getInventory() )  );
		db.setMarketable(isMarketable(db));
		
		return db;
		
	}
	
	public Long calcularQuantity (Inventory inv) {
		LOG.debug("Calculando Quantidade");
		Long tot = 0L;
		if ( inv != null) {
			List< WareHouse > lista =   inv.getWarehouses();
			for (WareHouse wareHouse : lista) {
				tot+=wareHouse.getQuantity();
			}
		}
		LOG.debug("Quantidade calculada:", tot);
		return tot;
	}
	
	public boolean isMarketable(  Product pro )  {
		return  pro.getInventory().getQuantity() > 0L ;
	}

	public void delete(Long sku) {
		productRepository.deleteById(sku);
	}

	public Product update(Product product) {

		LOG.info("Atualizando produto");
		LOG.info("Validação de SKU já existente");
		Product db = find(product.getSku());
		
		db.setName(product.getName());
		Inventory dbInv =   db.getInventory();
		dbInv.setWarehouses( product.getInventory().getWarehouses() );

		LOG.info("Alterando o Produto");
		return productRepository.save(db);

	}


}
