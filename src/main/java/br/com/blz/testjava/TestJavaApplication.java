package br.com.blz.testjava;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.blz.testjava.dominio.Inventory;
import br.com.blz.testjava.dominio.Sku;
import br.com.blz.testjava.dominio.WareHouses;
import br.com.blz.testjava.dominio.enums.TypeWareHouse;

@SpringBootApplication(scanBasePackageClasses = TestJavaApplication.class)
public class TestJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestJavaApplication.class, args);
		
		System.out.print("Carregando dominio inicial...");
		
		Inventory inventory = new Inventory();
		inventory.getWareHouses().add(new WareHouses("SP", 12, TypeWareHouse.ECOMMERCE));
		inventory.getWareHouses().add(new WareHouses("MOEMA", 3, TypeWareHouse.PHYSICAL_STORE));
		
		Sku sku = new Sku(
				43264L, 
				"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g", 
				inventory);
				
		System.out.println("ok !.");
	}
}
