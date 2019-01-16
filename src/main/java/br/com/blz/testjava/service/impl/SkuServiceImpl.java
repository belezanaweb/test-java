package br.com.blz.testjava.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.exception.DuplicateSkuExeception;
import br.com.blz.testjava.model.Sku;
import br.com.blz.testjava.repository.SkuRepository;
import br.com.blz.testjava.service.SkuService;

@Service
public class SkuServiceImpl implements SkuService{
	
	private static final Logger LOG = LoggerFactory.getLogger(SkuServiceImpl.class);
	
	@Autowired
	SkuRepository skuRepository;
	
	@Autowired
	CacheManager cacheManager;
	
	@Override
	public Sku getSku(Long id) {
		return skuRepository.findById(id);
	}

	@Override
	public Sku createSku(Sku sku) throws DuplicateSkuExeception {
		validateSkuExistance(sku.getId());
		
		LOG.info("Creating skull if id: "+ sku.getId());
		skuRepository.save(sku);
		
		refreshAllSkus();
		
		return sku;
	}

	@Override
	public Sku updateSku(Sku invetory) {
		skuRepository.save(invetory);
		
		return getSku(invetory.getId());
	}
	
	@Override
	public void deleteSku(Long sku) {
		Sku skuToDelete = getSku(sku);
		
		if(skuToDelete != null) {
			LOG.info("Getting all skulls from database");
			
			skuRepository.delete(skuToDelete);
			refreshAllSkus();
		}
	}

	@Override
	public List<Sku> getAllSkus() {
		LOG.info("Getting all skulls from database and setting in cache");
		
		return skuRepository.findAll();
	}
	
	private void validateSkuExistance(Long skuId) {
		LOG.info("Checking existance of skullId: "+ skuId);
		
		if(getSku(skuId) != null) {
			throw new DuplicateSkuExeception();
		}
	}
	
	
	private void refreshAllSkus() {
		LOG.info("Refreshing SkuAll Cache");
		cacheManager.getCache("SkuAllCache").clear();
		
		CompletableFuture.runAsync(() -> {
			getAllSkus();
		});
	}
}
