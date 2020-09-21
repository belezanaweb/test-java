package br.com.blz.testjava.utils;

import br.com.blz.testjava.model.SKU;
import br.com.blz.testjava.model.Warehouse;

public class Utils {

	// antes de retornar qualquer operacao, calcula o isMarketable
	public void checkIsMarketable(SKU sku) {
		if (sku != null) {
			if (sku.getInventory() != null && sku.getInventory().getQuantity() > 0) {
				sku.setIsMarketable(Boolean.TRUE);
			} else {
				sku.setIsMarketable(Boolean.FALSE);
			}
		}
	}

	// antes de retornar qualquer operacao, calcula o inventory.quantity
	public void countQuantity(SKU sku) {
		if (sku != null && sku.getInventory() != null) {
			int count = 0;
			if (sku.getInventory().getWarehouses() != null) {
				for (Warehouse warehouse : sku.getInventory().getWarehouses()) {
					count += warehouse.getQuantity();
				}
				sku.getInventory().setQuantity(count);
			} else {
				sku.getInventory().setQuantity(count);
			}
		}
	}

	// antes de qualquer operacao de insert ou update, seta null nos parametros
	// isMarketable e inventory.quantity
	public void prepareInsertUpdate(SKU sku) {
		if (sku != null) {
			sku.setIsMarketable(null);
			if (sku.getInventory() != null) {
				sku.getInventory().setQuantity(null);
			}
		}
	}

}
