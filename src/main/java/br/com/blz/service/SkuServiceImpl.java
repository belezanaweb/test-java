package br.com.blz.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.blz.model.Sku;

@Service
public class SkuServiceImpl implements SkuService {
	private static final Map<Long, Sku> mapSku = new HashMap<>();

	@Override
	public void create(Sku object) {

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
