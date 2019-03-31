package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exceptions.ProductAlreadyExistsException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Message;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.util.ResponseDefault;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ResponseDefault<Message> createProduct(Product product) throws ProductAlreadyExistsException {

		ResponseDefault<Message> response = null;
		
		if(product != null) {
			Product productExists = productRepository.findBySku(product.getSku());
			
			if(productExists == null) {
				
				productRepository.save(product);
				response = new ResponseDefault<Message>(Message.SUCCESS, 1L, "Produto salvo com sucesso.");
			}else {
				throw new ProductAlreadyExistsException();
			}
			
		}
			
		
		
		return response;
	}

	@Override
	public ResponseDefault<Message> alterProduct(Long sku, Product product) {

		ResponseDefault<Message> response = null;

		if (product != null) {
			try {
				Product productExists = productRepository.findBySku(sku);

				if (productExists != null) {
					product.setSku(sku);
					productRepository.save(product);
					response = new ResponseDefault<Message>(Message.SUCCESS, "Produto salvo com sucesso.");

				} else {

					response = new ResponseDefault<Message>(Message.NOT_FOUND,
							"Produto não pode ser atualizado pois ele não existe.");
				}

			} catch (Exception e) {
				response = new ResponseDefault<Message>(Message.ERROR, "Erro ao salvar o produto.");
			}

		}

		return response;

	}

	@Override
	public ResponseDefault<Message> deleteProduct(Long sku) {

		ResponseDefault<Message> response = null;

		try {
			Product product = productRepository.findBySku(sku);
			if (product != null) {
				productRepository.delete(sku);
				response = new ResponseDefault<Message>(Message.SUCCESS, 1L, "Produto deletado com sucesso");

			} else {
				response = new ResponseDefault<Message>(Message.NOT_FOUND, 0L, "Produto não encontrado");

			}

		} catch (Exception e) {
			response = new ResponseDefault<Message>(Message.SUCCESS, "Erro ao remover o produto.");
		}

		return response;

	}

	@Override
	public ResponseDefault<Product> findProduct(Long sku) {

		ResponseDefault<Product> response = new ResponseDefault<Product>(null, 0L, "Nenhum registro encontrado");
		Product product = productRepository.findBySku(sku);
		if (product != null) {
			response = new ResponseDefault<Product>(product, 1L, "OK");

			// Calc quantity
			if (product.getInventory() != null) {
				Inventory inverInventory = product.getInventory();

				Integer quantity = inverInventory.getWarehouses().stream()
						.mapToInt(wareHouse -> wareHouse.getQuantity()).sum();
				inverInventory.setQuantity(quantity);
				Boolean isMarketable = false;
				if (quantity > 0) {
					isMarketable = true;
				}
				product.setIsMarketable(isMarketable);
			}

		}

		return response;

	}

}
