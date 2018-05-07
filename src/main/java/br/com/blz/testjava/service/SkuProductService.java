package br.com.blz.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.SkuProduct;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.InventoryRepository;
import br.com.blz.testjava.repository.SkuProductRepository;

@Service
public class SkuProductService {

	@Autowired
	SkuProductRepository skuProductRepository;
	@Autowired
	SkuProduct skuProduct;
	@Autowired
	WarehouseService warehouseService;
	@Autowired
	Inventory inventory;
	@Autowired
	InventoryRepository inventoryRepository;

	public ResponseEntity<String> create(SkuProduct skuProduct) {

		if (skuProduct != null) {
			long contProductWarehouse = 0;
			if (skuProductRepository.existsById(skuProduct.getSku()) == false) {

				return new ResponseEntity<String>("Erro o produto já esta cadastrado", HttpStatus.CONFLICT);
			} else {
				this.skuProduct.setSku(skuProduct.getSku());
				this.skuProduct.setName(skuProduct.getName());
				inventory = skuProduct.getInventory();
				this.skuProduct.setWarehouse(skuProduct.getWarehouse());
				warehouseService.create(skuProduct.getWarehouse());

				for (Warehouse warehouse : skuProduct.getInventory().getWarehouses()) {

					contProductWarehouse += warehouse.getQuantity();

				}

				inventory.setQuantity(contProductWarehouse);

				if (contProductWarehouse < 0) {

					this.skuProduct.setMarketable(false);
				} else {

					this.skuProduct.setMarketable(true);
				}

				inventoryRepository.save(inventory);
				skuProductRepository.save(this.skuProduct);
				return new ResponseEntity<String>("Sucesso", HttpStatus.OK);

			}

		} else {
			return new ResponseEntity<String>("Json fora do formato", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<String> delete(SkuProduct skuProduct) {

		if (skuProductRepository.existsById(skuProduct.getSku())) {

			skuProductRepository.delete(skuProduct);

			return new ResponseEntity<String>("Sucesso", HttpStatus.OK);

		} else {

			return new ResponseEntity<String>("Sku não localizado!", HttpStatus.NOT_FOUND);
		}

	}

	public List<SkuProduct> listskuProduct() {
		return skuProductRepository.findAll();

	}

	public ResponseEntity<String> updateProduct(SkuProduct skuProduct) {

		if (skuProductRepository.existsById(skuProduct.getSku())) {
			SkuProduct skuProductToUpdate = skuProductRepository.getOne(skuProduct.getSku());
			skuProductToUpdate = skuProduct;
			skuProductRepository.save(skuProductToUpdate);
			return new ResponseEntity<String>("Sucesso", HttpStatus.ACCEPTED);

		} else {

			return new ResponseEntity<String>("Sku não localizado!", HttpStatus.NOT_FOUND);
		}

	}
}
