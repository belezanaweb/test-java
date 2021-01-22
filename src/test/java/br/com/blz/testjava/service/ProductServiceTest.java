package br.com.blz.testjava.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.blz.testjava.domain.Inventory;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.exception.BusinessException;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductService productServiceMock;

	private Product product;

	@Before
	public void init() {

		product = new Product();
		product.setSku(43264L);
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");

		List<Warehouse> warehouses = new ArrayList<Warehouse>();

		Warehouse warehouse = new Warehouse();
		warehouse.setLocality("SP");
		warehouse.setQuantity(12);
		warehouse.setType("ECOMMERCE");
		warehouses.add(warehouse);

		warehouse = new Warehouse();
		warehouse.setLocality("MOEMA");
		warehouse.setQuantity(3);
		warehouse.setType("PHYSICAL_STORE");
		warehouses.add(warehouse);

		Inventory inventory = new Inventory();
		inventory.setWarehouses(warehouses);
		product.setInventory(inventory);

	}

	/**
	 * 
	 * Teste garante o somatório da quantity dos warehouses na propriedade
	 * inventory.quantity e ganrante um produto marketable true sempre que seu
	 * inventory.quantity for maior que 0
	 * @throws Exception 
	 */
	@Test
	public void validSumNumberOfwarehousesTest() throws Exception {

		product.setSku(43266L);
		productServiceMock.insert(product);
		Product productCalculado = productServiceMock.recoverProductBySku(product.getSku());
		Integer resultadoEsperadoPosSomatorio = 15;
		assertEquals(resultadoEsperadoPosSomatorio, productCalculado.getInventory().getQuantity());
		assertEquals(Boolean.TRUE, productCalculado.getIsMarketable());
	}

	/**
	 * 
	 * Teste garante um produto marketable false sempre que seu inventory.quantity
	 * for igual que 0
	 * @throws Exception 
	 * 
	 */
	@Test
	public void validSumNumberZeroOfwarehousesTest() throws Exception {

		for (Warehouse warehouse : product.getInventory().getWarehouses()) {
			warehouse.setQuantity(BigDecimal.ZERO.intValue());
		}

		productServiceMock.insert(product);
		Product productCalculado = productServiceMock.recoverProductBySku(product.getSku());
		Integer resultadoEsperadoPosSomatorio = 0;
		assertEquals(resultadoEsperadoPosSomatorio, productCalculado.getInventory().getQuantity());
		assertEquals(Boolean.FALSE, productCalculado.getIsMarketable());
	}

	/**
	 * Teste garante que não ocorra inserção duplicada com mesmo sku
	 * @throws Exception 
	 */
	@Test
	public void insertDuplicateProductTest() throws Exception {

		String messageException = null;

		try {
			productServiceMock.insert(product);
			productServiceMock.insert(product);

		} catch (BusinessException e) {

			messageException = e.getMessage();
		}

		assertEquals("Produto já existente com sku informado!", messageException);
	}

	/**
	 * 
	 * Teste garante a edição do produto
	 * @throws Exception 
	 */
	@Test
	public void updateProductTest() throws Exception {

		product.setSku(43265L);
		productServiceMock.insert(product);
		
		Product productModificado =  new Product();
		
		productModificado.setSku(43265L);
		productModificado.setName("Nome do produto alterado");
		
		productServiceMock.update(productModificado);
		
		Product productAlterado = productServiceMock.findProductBySku(43265L);
		assertEquals(43265L, productAlterado.getSku());
		assertEquals("Nome do produto alterado", productAlterado.getName());
	}
	
	/**
	 * 
	 * Teste garante o lançamento de exceção na tentativa de editar produto com sku inexistente
	 * @throws Exception 
	 */
	@Test
	public void updateProductSkuInvalidTest() throws Exception  {

		product.setSku(77778L);
		productServiceMock.insert(product);
		
		Product productAlterado = new Product();		
		productAlterado.setName("Nome do produto alterado");
		productAlterado.setSku(99999L);
		
		String messageException = null;
		try {
			productServiceMock.update(productAlterado);
		} catch (BusinessException e) {
			messageException = e.getMessage();
		}
	
		assertEquals("Produto com sku informado não encontrado para edição!", messageException);
	}
	
	/**
	 * 
	 * Teste garante a remoção do produto
	 * @throws Exception 
	 */
	@Test
	public void deleteProductTest() throws Exception {
		
		productServiceMock.insert(product);		
		Product product1 = productServiceMock.findProductBySku(43264L);		
		assertEquals(43264L, product1.getSku());
		Boolean deleted = productServiceMock.delete(43264L);
		assertEquals(Boolean.TRUE, deleted);
		Product product2 = productServiceMock.findProductBySku(43264L);
		assertEquals(null, product2);
	}

}
