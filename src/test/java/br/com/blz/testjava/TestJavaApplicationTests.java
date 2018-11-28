package br.com.blz.testjava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.controller.ProductsController;
import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WarehouseDTO;
import br.com.blz.testjava.enumeration.WarehouseTypeEnum;
import br.com.blz.testjava.exception.InvalidDataException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJavaApplicationTests {

	@Autowired 
	ProductsController controller;
	
	@Test
	public void testCreate() {
		
		ProductDTO prodDTO;
		ResponseEntity<ProductDTO> respOK;
		
		prodDTO = new ProductDTO();
		prodDTO.setName("Shampoo");
		this.addWarehouse(prodDTO, "SP", 15, WarehouseTypeEnum.ECOMMERCE);
		this.addWarehouse(prodDTO, "MG", 25, WarehouseTypeEnum.ECOMMERCE);
		
		respOK = controller.create(prodDTO);
		
		assertNotNull(respOK);
		assertEquals(respOK.getStatusCode(), HttpStatus.OK);
		assertEquals(respOK.getBody().getInventory().getQuantity(), new Integer(40)); //A propriedade inventory.quantity é a soma da quantity dos warehouses (15 + 25) 
		assertEquals(respOK.getBody().getIsMarketable(), Boolean.TRUE); //Um produto é marketable sempre que seu inventory.quantity for maior que 0

		prodDTO = new ProductDTO();
		prodDTO.setName("Sabonete");
		this.addWarehouse(prodDTO, "SP", 0, WarehouseTypeEnum.PHYSICAL_STORE);
		this.addWarehouse(prodDTO, "MG", 0, WarehouseTypeEnum.PHYSICAL_STORE);

		respOK = controller.create(prodDTO);
		
		assertNotNull(respOK);
		assertEquals(respOK.getStatusCode(), HttpStatus.OK);
		assertEquals(respOK.getBody().getInventory().getQuantity(), new Integer(0)); //A propriedade inventory.quantity é a soma da quantity dos warehouses 
		assertEquals(respOK.getBody().getIsMarketable(), Boolean.FALSE); //Um produto é marketable sempre que seu inventory.quantity for maior que 0

	}
	
	@Test
	public void testValidationCreate(){

		ProductDTO prodDTO;
		InvalidDataException dataException;

		prodDTO = new ProductDTO();
		prodDTO.setName("Shampoo");
		dataException = this.createProductWithInvalidData(prodDTO);
		assert(dataException.getMessages().size() > 0); //sem inventario
		
		prodDTO = new ProductDTO();
		prodDTO.setName("Shampoo");
		this.addWarehouse(prodDTO, null, 10, WarehouseTypeEnum.ECOMMERCE);
		dataException = this.createProductWithInvalidData(prodDTO);
		assert(dataException.getMessages().size() > 0); //sem localidade
		
		prodDTO = new ProductDTO();
		prodDTO.setName("Shampoo");
		this.addWarehouse(prodDTO, "SP", null, WarehouseTypeEnum.ECOMMERCE);
		dataException = this.createProductWithInvalidData(prodDTO);
		assert(dataException.getMessages().size() > 0); //sem quantidade

		prodDTO = new ProductDTO();
		prodDTO.setName("Shampoo");
		this.addWarehouse(prodDTO, "SP", 10, null);
		dataException = this.createProductWithInvalidData(prodDTO);
		assert(dataException.getMessages().size() > 0); //sem tipo
		
		prodDTO = new ProductDTO();
		prodDTO.setName("Shampoo");
		this.addWarehouse(prodDTO, "SP", 10, WarehouseTypeEnum.ECOMMERCE);
		dataException = this.createProductWithInvalidData(prodDTO);
		assertNull(dataException); // VALIDACAO OK
	}
	
	@Test
	public void testUpdate(){
		
		ProductDTO prodDTO, prodDTOUpdated;
		ResponseEntity<ProductDTO> respOK;

		prodDTO = new ProductDTO();
		prodDTO.setName("Esmalte");
		this.addWarehouse(prodDTO, "SP", 0, WarehouseTypeEnum.PHYSICAL_STORE);
		
		respOK = controller.create(prodDTO);
		prodDTO = respOK.getBody();
		assertEquals(respOK.getStatusCode(), HttpStatus.OK);
		assertEquals(prodDTO.getInventory().getQuantity(), new Integer(0)); 
		assertEquals(prodDTO.getIsMarketable(), Boolean.FALSE);

		prodDTOUpdated = new ProductDTO();
		prodDTOUpdated.setName("Esmalte");
		this.addWarehouse(prodDTOUpdated, "RJ", 100, WarehouseTypeEnum.ECOMMERCE);
		
		respOK = controller.update(prodDTO.getSku(), prodDTOUpdated);
		prodDTOUpdated = respOK.getBody(); 
		assertEquals(respOK.getStatusCode(), HttpStatus.OK);
		assertEquals(prodDTOUpdated.getInventory().getQuantity(), new Integer(100)); 
		assertEquals(prodDTOUpdated.getIsMarketable(), Boolean.TRUE);

		//Dois produtos são considerados iguais se os seus skus forem iguais
		//Ao atualizar um produto, o antigo deve ser sobrescrito com o que esta sendo enviado na requisição
		assertEquals(prodDTO.getSku(), prodDTOUpdated.getSku());
	}
	
	private void addWarehouse(ProductDTO dto, String locality, Integer quantity, WarehouseTypeEnum type){
		
		List<WarehouseDTO> warehouses;
		
		if (dto.getInventory() == null){
			warehouses = new ArrayList<WarehouseDTO>();
			
			dto.setInventory(new InventoryDTO());
			dto.getInventory().setWarehouses(warehouses);
		
		} else {
			if (dto.getInventory().getWarehouses() == null){
				warehouses = new ArrayList<WarehouseDTO>();
				dto.getInventory().setWarehouses(warehouses);
			
			} else {
				warehouses = dto.getInventory().getWarehouses();
			}
		}
		
		WarehouseDTO wareDTO = new WarehouseDTO(); 
		wareDTO.setLocality(locality);
		wareDTO.setQuantity(quantity);
		wareDTO.setType(type);
		warehouses.add(wareDTO);
	}
	
	private InvalidDataException createProductWithInvalidData(ProductDTO dto){
		
		InvalidDataException ex = null;
		
		try {
			this.controller.create(dto);
			
		} catch (InvalidDataException e) {
			ex = e;
		}
		return ex;
	}

}
