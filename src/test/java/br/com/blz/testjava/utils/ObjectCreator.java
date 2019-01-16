package br.com.blz.testjava.utils;


import java.util.ArrayList;
import java.util.List;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Sku;
import br.com.blz.testjava.model.WarehouseProduct;
import br.com.blz.testjava.model.enumeration.WarehouseType;;


public class ObjectCreator {
	
	public static Sku createSku(Long id, String nome, Inventory inventory){
		return new Sku(id, nome, inventory);
	}
	
	public static Sku getSku(){
		return createSku(10l, "Product 1", getInventory());
	}
	
	public static Sku getSkuNoMarketable(){
		List<WarehouseProduct> warehouseProductsNoMarketable = new ArrayList<>();
		warehouseProductsNoMarketable.add(createWarehouseProduct("Boston", 0, WarehouseType.PHYSICAL_STORE));
		warehouseProductsNoMarketable.add(createWarehouseProduct("Revere", 0, WarehouseType.PHYSICAL_STORE));
		
		return createSku(10l, "Product NO MARKETABLE", createInventory(warehouseProductsNoMarketable));
	}
	
	public static WarehouseProduct createWarehouseProduct(String locality, Integer quantity, WarehouseType warehouseType){
		return new WarehouseProduct(locality, quantity, warehouseType);
	}
	
	public static WarehouseProduct getWarehouseProduct(){
		return createWarehouseProduct("Jaguare", 20, WarehouseType.ECOMMERCE);
	}
	
	public static List<WarehouseProduct> getWarehouseProductList(){
		List<WarehouseProduct> warehouseProducts = new ArrayList<>();
		warehouseProducts.add(createWarehouseProduct("Boston", 20, WarehouseType.PHYSICAL_STORE));
		warehouseProducts.add(getWarehouseProduct());
		
		return warehouseProducts;
	}
	
	public static Inventory getInventory(){
		return new Inventory(getWarehouseProductList());
	}
	
	public static Inventory createInventory(List<WarehouseProduct> warehouseProductList){
		return new Inventory(warehouseProductList);
	}
	
	public static List<Sku> getSkuList(){
		List<Sku> skuList = new ArrayList<>();
		skuList.add(createSku(20l, "Product 2", getInventory()));
		skuList.add(getSku());
		
		return skuList;
	}
}
