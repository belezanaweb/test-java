package br.com.blz.testjava;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.assertj.core.internal.cglib.core.WeakCacheKey;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.WareHouse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJavaApplicationTests {

	@Autowired
	private EndPoint endpoint;
	private Product loadProduct(){
		Product product = new Product();
		product.setSku(43264);
		product.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");

		WareHouse wareHouse1 = new WareHouse();
		wareHouse1.setLocality("SP");
		wareHouse1.setQuantity(10);
		wareHouse1.setType("ECOMMERCE");
		
		WareHouse wareHouse2 = new WareHouse();
		wareHouse2.setLocality("SP");
		wareHouse2.setQuantity(10);
		wareHouse2.setType("ECOMMERCE");

		Inventory inventory = new Inventory();
		ArrayList<WareHouse> wareHouses = new ArrayList<WareHouse>();
		wareHouses.add(wareHouse1);
		wareHouses.add(wareHouse2);
		inventory.setWareHouses(wareHouses);
		return product;
	}

	@Before
	public void clearList(){
		endpoint.deleteProduct(loadProduct());
	}

	@Test
	public void contextLoads() {
		assertThat(endpoint).isNotNull();
	}


	@Test
	public void shouldSaveProduct(){
		ResponseEntity<?> response = endpoint.addProduct(loadProduct());
		Product productAdd = (Product) response.getBody();
		assertThat(productAdd.getSku()).isEqualTo(loadProduct().getSku());
	}

	@Test
	public void shouldVerifyProductExistsToAdd(){
		endpoint.addProduct(loadProduct());
		ResponseEntity<?> response = endpoint.addProduct(loadProduct());
		Map<String,String> mensagem = (Map<String, String>) response.getBody();
		String msg = mensagem.get("mensagem");
		assertThat(msg).isEqualTo("Produto Informado ja se encontra cadastrado");
	}

	@Test
	public void shouldEditProduct(){
		endpoint.addProduct(loadProduct());
		ResponseEntity<?> response = endpoint.editProduct(loadProduct());
		Product productAdd = (Product) response.getBody();
		assertThat(productAdd.getSku()).isEqualTo(loadProduct().getSku());
	}
	@Test
	public void shouldValidateIfProductDoesntExists(){
		ResponseEntity<?> response = endpoint.editProduct(loadProduct());
		Map<String,String> mensagem = (Map<String, String>) response.getBody();
		String msg = mensagem.get("mensagem");
		assertThat(msg).isEqualTo("Produto Informado não existente");
	}

	@Test
	public void shoultReturnProduct(){
		endpoint.addProduct(loadProduct());
		ResponseEntity<?> response = endpoint.findProduct(43264);
		Product product = (Product) response.getBody();
		assertThat(product.getSku()).isEqualTo(loadProduct().getSku());
	}

	@Test
	public void shoultValidateProductExistsToReturn(){
		ResponseEntity<?> response = endpoint.findProduct(43264);
		Map<String,String> mensagem = (Map<String, String>) response.getBody();
		String msg = mensagem.get("mensagem");
		assertThat(msg).isEqualTo("Produto Informado não existente");
	}

	@Test
	public void shoultDeleteProduct(){
		endpoint.addProduct(loadProduct());
		ResponseEntity<?> response = endpoint.deleteProduct(loadProduct());
		Map<String,String> mensagem = (Map<String, String>) response.getBody();
		String msg = mensagem.get("mensagem");
		assertThat(msg).isEqualTo("Produto Informado foi excluido com sucesso");
	}

	@Test
	public void shoultValidateIfProdutDoesntExistsToDelete(){
		ResponseEntity<?> response = endpoint.deleteProduct(loadProduct());
		Map<String,String> mensagem = (Map<String, String>) response.getBody();
		String msg = mensagem.get("mensagem");
		assertThat(msg).isEqualTo("Produto Informado não existente");
	}
}
