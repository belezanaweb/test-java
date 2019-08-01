package br.com.blz.testjava;

import br.com.blz.testjava.object.Inventory;
import br.com.blz.testjava.object.Product;
import br.com.blz.testjava.object.Warehouses;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.repository.WarehouseRepository;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	WarehouseRepository warehouseRepository;

	private void save(Product product) {
		productRepository.save(product);
		product.getInventory().getWarehouses().forEach(werehouse -> {
			warehouseRepository.save(werehouse);
		});

	}

	public void create(Product product) {

		if (Objects.isNull(product.getSku())) {
			throw new IllegalArgumentException();
		}

		Product productFound = productRepository.findOne(product.getSku());
		if (Objects.nonNull(productFound)) {
			throw new RuntimeException("Product Already Exist");
		}
		save(product);
	}

	public void update(Product product) {
		Product productFound = productRepository.findOne(product.getSku());
		if (Objects.isNull(productFound)) {
			throw new RuntimeException("Product not exist in database");
		}
		warehouseRepository.deleteByProductSku(product.getSku());
		save(product);
	}

	public Product getBySku(Long sku) {
		Product product = productRepository.findOne(sku);

		Inventory inventory = new Inventory();
		List<Warehouses> warehouses = warehouseRepository.findByProductSku(product.getSku());
		inventory.setWarehouses(warehouses);

		product.setInventory(inventory);
		return product;
	}

	public void deleteBySku(Long id) {
		warehouseRepository.deleteByProductSku(id);
		productRepository.delete(id);
	}

	public List<Product> getAll() {
		return productRepository.findAll();
	}
}