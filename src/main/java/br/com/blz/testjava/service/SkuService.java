package br.com.blz.testjava.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import br.com.blz.testjava.exception.DuplicateSkuExeception;
import br.com.blz.testjava.model.Sku;

public interface SkuService {
	
	@Cacheable("SkuCache")
	public Sku getSku(Long skuId);
	
	@Cacheable("SkuCache") 
	public Sku createSku(Sku sku) throws DuplicateSkuExeception;
	
	@Cacheable("SkuCache") 
	public Sku updateSku(Sku sku);
	
	@Cacheable("SkuCache") 
	public void deleteSku(Long skuId);
	
	@Cacheable("SkuAllCache" )
	public List<Sku> getAllSkus();
	
}
