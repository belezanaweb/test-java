package br.com.blz.testjava.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.SkuAlreadyExistsException;
import br.com.blz.testjava.exception.SkuNotFoundException;
import br.com.blz.testjava.model.Product;

@Service
public class SkuServiceImpl implements SkuService {

	private HashMap<Long, Product> persisted = new HashMap<>();

	@Override
	public void create(Product product) throws SkuAlreadyExistsException {
		if(persisted.containsKey(product.getSku())) {
			throw new SkuAlreadyExistsException();
		}
		persisted.put(product.getSku(), product);
	}

	@Override
	public void update(Product product) throws SkuNotFoundException {
		if(!persisted.containsKey(product.getSku())) {
			throw new SkuNotFoundException();
		}
		persisted.put(product.getSku(), product);
	}

	@Override
	public void remove(Long skuId) throws SkuNotFoundException {
		if(!persisted.containsKey(skuId)) {
			throw new SkuNotFoundException();
		}
		persisted.remove(skuId);
	}

	@Override
	public Product read(Long skuId) throws SkuNotFoundException {
		if(!persisted.containsKey(skuId)) {
			throw new SkuNotFoundException();
		}
		return persisted.get(skuId);
	}

}
