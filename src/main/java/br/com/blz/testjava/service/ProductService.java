package br.com.blz.testjava.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.InventoryWarehouse;
import br.com.blz.testjava.model.InventoryWarehouseKey;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.ProductTO;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.InventoryRepository;
import br.com.blz.testjava.repository.InventoryWarehouseRepository;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private WarehouseService warehouseService;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private InventoryWarehouseRepository inventoryWarehouseRepository;

	public List<ProductTO> listAll() {
		return ((List<Product>) productRepository.findAll()).stream().map(product -> product.getProductTO())
				.collect(Collectors.toList());
	}

	public ProductTO addProduct(ProductTO productTo) {

		try {
			Inventory inventory = new Inventory();

			Inventory savedInventory = inventoryRepository.save(inventory);

			productTo.getInventoryTo().getWarehousesTO().stream().map(warehouseto -> {
				Warehouse savedWarehouse = warehouseService
						.findOrCreate(new Warehouse(warehouseto.getLocality(), warehouseto.getType()));

				InventoryWarehouseKey inventoryWarehouseKey = new InventoryWarehouseKey();

				inventoryWarehouseKey.setInventoryId(inventory.getId());
				inventoryWarehouseKey.setWarehouseId(savedWarehouse.getId());

				return inventoryWarehouseRepository.save(new InventoryWarehouse(inventoryWarehouseKey, inventory,
						savedWarehouse, warehouseto.getQuantity()));
			}).collect(Collectors.toList());

			Product product = new Product();

			product.setSku(productTo.getSku());
			product.setName(productTo.getName());

			product.setInventory(savedInventory);

			productRepository.save(product);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("sku informado já existe!");
		}
		
		return productTo;

	}

	public synchronized ProductTO updateProduct(int sku, ProductTO productTO) {

		Product findBySku = productRepository.findBySku(sku);

		productTO.getInventoryTo().getWarehousesTO().stream().map(warehouseto -> {
			Warehouse savedWarehouse = warehouseService
					.findOrCreate(new Warehouse(warehouseto.getLocality(), warehouseto.getType()));

			InventoryWarehouseKey inventoryWarehouseKey = new InventoryWarehouseKey();

			inventoryWarehouseKey.setInventoryId(findBySku.getInventory().getId());
			inventoryWarehouseKey.setWarehouseId(savedWarehouse.getId());

			return inventoryWarehouseRepository.save(new InventoryWarehouse(inventoryWarehouseKey,
					findBySku.getInventory(), savedWarehouse, warehouseto.getQuantity()));
		}).collect(Collectors.toList());

		findBySku.setSku(productTO.getSku());
		findBySku.setName(productTO.getName());

		Product save = productRepository.save(findBySku);

		return save.getProductTO();

	}

	public synchronized void deleteProduct(int sku) {
		Product findBySku = productRepository.findBySku(sku);

		inventoryWarehouseRepository.delete(findBySku.getInventory().getWarehouses());

		int inventoryId = findBySku.getInventory().getId();

		productRepository.delete(findBySku);

		inventoryRepository.delete(inventoryId);

	}

	public ProductTO getProduct(int sku) {
		Product product = productRepository.findBySku(sku);

		return product.getProductTO();
	}
}
