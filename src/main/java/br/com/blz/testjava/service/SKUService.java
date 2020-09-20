package br.com.blz.testjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dao.SKUDAO;
import br.com.blz.testjava.model.SKU;
import br.com.blz.testjava.utils.Utils;

@Service
public class SKUService {

	@Autowired
	private SKUDAO dao;

	private Utils util = new Utils();

	public List<SKU> getAll() throws Exception {
		List<SKU> list = dao.getAll();
		if (!list.isEmpty()) {
			for (SKU sku : list) {
				util.countQuantity(sku);
				util.checkIsMarketable(sku);
			}
		}
		return list;
	}

	public SKU get(Integer key) throws Exception {
		SKU sku = dao.get(key);
		if (sku != null) {
			util.countQuantity(sku);
			util.checkIsMarketable(sku);
		}
		return sku;
	}

	public SKU insert(SKU param) throws Exception {
		util.prepareInsertUpdate(param);
		boolean status = dao.insert(param);
		if (status) {
			util.countQuantity(param);
			util.checkIsMarketable(param);
			return param;
		} else {
			return null;
		}
	}

	public SKU delete(Integer key) throws Exception {
		return dao.delete(key);
	}

	public SKU update(Integer key, SKU param) throws Exception {
		util.prepareInsertUpdate(param);
		boolean status = dao.update(key, param);
		if (status) {
			util.countQuantity(param);
			util.checkIsMarketable(param);
			return param;
		} else {
			return null;
		}
	}
}
