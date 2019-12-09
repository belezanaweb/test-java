package br.com.blz.testjava.repository;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.blz.testjava.dto.SKUDTO;

@Component
@Scope("singleton")
public class SKURepository {

	public HashMap<Integer, SKUDTO> list;

	public SKURepository() {
		list = new HashMap<Integer, SKUDTO>();
	}

	public SKUDTO createSKU(SKUDTO skuDTO) throws Exception {
		if (list.containsKey(skuDTO.getSku())) {

			throw new Exception("SKU já existente na base", null);
		}

		return list.put(skuDTO.getSku(), skuDTO);
	}

	public SKUDTO getSKU(int sku) {
		return list.get(sku);
	}

	public SKUDTO updateSKU(SKUDTO skuDTO) {
		return list.replace(skuDTO.getSku(), skuDTO);
	}
	
	public SKUDTO deleteSKU(int sku) {
		return list.remove(sku);
	}
}
