package br.com.blz.testjava.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.InventoryWarehouse;
import br.com.blz.testjava.entity.InventoryWarehouseKey;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;

import br.com.blz.testjava.repository.InventoryRepository;
import br.com.blz.testjava.repository.InventoryWarehouseRepository;
import br.com.blz.testjava.repository.ProductRepository;
import dto.ProductDTO;

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

	public List<ProductDTO> listAll() {
		return ((List<Product>) productRepository.findAll()).stream().map(product -> product.getProductTO())
				.collect(Collectors.toList());
	}

	public ProductDTO addProduct(ProductDTO dto) {

		try {
			Inventory inventory = new Inventory();

			Inventory savedInventory = inventoryRepository.save(inventory);

			dto.getInventoryDto().getWarehousesDTO().stream().map(warehouseto -> {
				Warehouse savedWarehouse = warehouseService
						.findOrCreate(new Warehouse(warehouseto.getLocality(), warehouseto.getType()));

				InventoryWarehouseKey inventoryWarehouseKey = new InventoryWarehouseKey();

				inventoryWarehouseKey.setInventoryId(inventory.getId());
				inventoryWarehouseKey.setWarehouseId(savedWarehouse.getId());

				return inventoryWarehouseRepository.save(new InventoryWarehouse(inventoryWarehouseKey, inventory,
						savedWarehouse, warehouseto.getQuantity()));
			}).collect(Collectors.toList());

			Product product = new Product();

			product.setSku(dto.getSku());
			product.setName(dto.getName());

			product.setInventory(savedInventory);

			productRepository.save(product);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("sku ja existe!");
		}
		
		return dto;

	}

	public synchronized ProductDTO updateProduct(Long sku, ProductDTO dto) {

		Product findBySku = productRepository.findBySku(sku);

		dto.getInventoryDto().getWarehousesDTO().stream().map(warehouseto -> {
			Warehouse savedWarehouse = warehouseService
					.findOrCreate(new Warehouse(warehouseto.getLocality(), warehouseto.getType()));

			InventoryWarehouseKey inventoryWarehouseKey = new InventoryWarehouseKey();

			inventoryWarehouseKey.setInventoryId(findBySku.getInventory().getId());
			inventoryWarehouseKey.setWarehouseId(savedWarehouse.getId());

			return inventoryWarehouseRepository.save(new InventoryWarehouse(inventoryWarehouseKey,
					findBySku.getInventory(), savedWarehouse, warehouseto.getQuantity()));
		}).collect(Collectors.toList());

		findBySku.setSku(dto.getSku());
		findBySku.setName(dto.getName());

		Product save = productRepository.save(findBySku);

		return save.getProductTO();

	}

	public synchronized void deleteProduct(Long sku) {
		Product findBySku = productRepository.findBySku(sku);

		inventoryWarehouseRepository.delete(findBySku.getInventory().getWarehouses());

		long inventoryId = findBySku.getInventory().getId();

		inventoryRepository.delete(inventoryId);

		productRepository.delete(findBySku);

	}

	public ProductDTO getProduct(Long sku) {
		Product product = productRepository.findBySku(sku);

		return product.getProductTO();
	}
}
