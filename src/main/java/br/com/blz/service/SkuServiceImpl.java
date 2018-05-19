package br.com.blz.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.blz.exception.CrudException;
import br.com.blz.model.Sku;

@Service
public class SkuServiceImpl implements SkuService {
	private static final Map<Long, Sku> mapSku = new HashMap<>();

	@Override
	public void create(Sku newSku) throws CrudException {
		Sku sku = SkuServiceImpl.mapSku.get(newSku.getSku());
		if( sku != null ) {
			throw new CrudException("Já existe um Sku cadastrado para esse codigo.");
		}
		SkuServiceImpl.mapSku.put(newSku.getSku(), newSku);
	}

	@Override
	public void delete(Long id) {
		SkuServiceImpl.mapSku.remove(id);
	}

	@Override
	public Sku byId(Long id) {
		return SkuServiceImpl.mapSku.get(id);
	}

	@Override
	public void update(Sku object) {
		SkuServiceImpl.mapSku.put(object.getSku(), object);
	}

}
