package br.com.blz.testjava.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.WareHouse;
import br.com.blz.testjava.service.ProductDuplicateException;
import br.com.blz.testjava.service.ProductNotFound;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.type.WareHouseType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {

	@Mock
	private IProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Before
	public void setUp() {

		Product prodSave = new Product();
		prodSave.setSku(1333L);
		prodSave.setName("Produto salvo");

		Product prod1 = new Product();
		prod1.setSku(1L);
		prod1.setName("Nome do produto 1");

		Inventory inv1 = new Inventory();
		WareHouse wr1 = new WareHouse();
		wr1.setLocality("PR");
		wr1.setQuantity(1);
		wr1.setType(WareHouseType.ECOMMERCE);

		WareHouse wr2 = new WareHouse();
		wr2.setLocality("SP");
		wr2.setQuantity(3);
		wr2.setType(WareHouseType.PHYSICAL_STORE);
		inv1.getWarehouses().add(wr1);
		inv1.getWarehouses().add(wr2);

		Mockito.when(productRepository.findById(prod1.getSku())).thenReturn(Optional.of(prod1));

		Mockito.when(productRepository.existsById(prod1.getSku())).thenReturn(true);

		Mockito.when(productRepository.save(Mockito.any(Product.class))).thenAnswer(i -> i.getArgument(0));
		
		
	}

	@Test(expected = ProductDuplicateException.class)
	public void createProductDuplicateTest() {
		
		
		Product prodTeste = new Product();
		prodTeste.setSku(1L);
		prodTeste.setName("Nome do produto 1");

		Inventory invTeste = new Inventory();
		WareHouse wrTeste = new WareHouse();
		wrTeste.setLocality("PR");
		wrTeste.setQuantity(1);
		wrTeste.setType(WareHouseType.ECOMMERCE);

		WareHouse wrTeste2 = new WareHouse();
		wrTeste2.setLocality("SP");
		wrTeste2.setQuantity(3);
		wrTeste2.setType(WareHouseType.PHYSICAL_STORE);
		invTeste.getWarehouses().add(wrTeste);
		invTeste.getWarehouses().add(wrTeste2);

		 productService.create(prodTeste);


	}
	
	@Test
	public void createProductTest() {
		
		
		Product prodTeste = new Product();
		prodTeste.setSku(2L);
		prodTeste.setName("Nome do produto 2");
		
		Inventory invTeste = new Inventory();
		WareHouse wrTeste = new WareHouse();
		wrTeste.setLocality("PR");
		wrTeste.setQuantity(1);
		wrTeste.setType(WareHouseType.ECOMMERCE);
		
		WareHouse wrTeste2 = new WareHouse();
		wrTeste2.setLocality("SP");
		wrTeste2.setQuantity(3);
		wrTeste2.setType(WareHouseType.PHYSICAL_STORE);
		invTeste.getWarehouses().add(wrTeste);
		invTeste.getWarehouses().add(wrTeste2);
		prodTeste.setIventory(invTeste);
		
		Product db =productService.create(prodTeste);
		
		assertEquals(prodTeste.getSku(), db.getSku());
		assertEquals(prodTeste.getName(), db.getName());
		
		
	}

	@Test
	public void findProductTest() {
		
		
		Product prodTeste = new Product();
		prodTeste.setSku(1L);
		
		Product resul = productService.find(prodTeste.getSku());
		assertEquals(resul.getSku(), prodTeste.getSku());
		
		
	}
	
	@Test(expected = ProductNotFound.class)
	public void findProductExceptionTest() {
		
		Product prodTeste = new Product();
		prodTeste.setSku(3L);
		
		productService.find(prodTeste.getSku());
		
	}

	
	@Test
	public void updateProductTest() {
		
		
		Product prodTeste = new Product();
		prodTeste.setSku(1L);
		prodTeste.setName("Nome do produto 1 - alterado");

		Inventory invTeste = new Inventory();
		WareHouse wrTeste = new WareHouse();
		wrTeste.setLocality("PR");
		wrTeste.setQuantity(1);
		wrTeste.setType(WareHouseType.ECOMMERCE);

		WareHouse wrTeste2 = new WareHouse();
		wrTeste2.setLocality("SP");
		wrTeste2.setQuantity(3);
		wrTeste2.setType(WareHouseType.PHYSICAL_STORE);
		invTeste.getWarehouses().add(wrTeste);
		invTeste.getWarehouses().add(wrTeste2);
		prodTeste.setIventory(invTeste);

		Product db = productService.update(prodTeste);
		
		assertEquals(prodTeste.getName(), db.getName());


	}
	
	@Test(expected = ProductNotFound.class)
	public void updateProductNotFoundTest() {
		
		
		Product prodTeste = new Product();
		prodTeste.setSku(3L);
		prodTeste.setName("Nome do produto 3 - alterado");
		
		
		Product db = productService.update(prodTeste);
		
		assertEquals(prodTeste.getName(), db.getName());
		
		
	}
	
	
	@Test
	public void calcularQuantityTest() {
		
		Inventory inv1 = new Inventory();
		WareHouse wr1 = new WareHouse();
		wr1.setLocality("PR");
		wr1.setQuantity(10);

		WareHouse wr2 = new WareHouse();
		wr2.setLocality("SP");
		wr2.setQuantity(23);
		inv1.getWarehouses().add(wr1);
		inv1.getWarehouses().add(wr2);
		
		assertEquals (  productService.calcularQuantity(inv1), 33); 
		
	}
	
	@Test
	public void isMarketableTrueTest() {
		
		Product prodTeste = new Product();
		prodTeste.setSku(1L);
		prodTeste.setName("Nome do produto 1 - alterado");

		Inventory invTeste = new Inventory();
		invTeste.setQuantity( 10L );
		prodTeste.setIventory(invTeste);
		
		
		
		assertEquals ( productService.isMarketable(prodTeste), true  ); 
		
	}

	
	@Test
	public void isMarketableFalseTest() {
		
		Product prodTeste = new Product();
		prodTeste.setSku(1L);
		prodTeste.setName("Nome do produto 1 - alterado");

		Inventory invTeste = new Inventory();
		invTeste.setQuantity( 0L );
		
		
		assertEquals ( productService.isMarketable(prodTeste), false  ); 
		
	}

		
}
