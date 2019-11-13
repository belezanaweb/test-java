package br.com.blz.testjava;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouses;
import br.com.blz.testjava.repository.ProdutoRepository;
import br.com.blz.testjava.service.ProdutoServiceImpl;

public class ProdutoServiceTest {
	
	@Mock
	private ProdutoRepository repository;
	
	@InjectMocks
	private ProdutoServiceImpl service;
	
	private Product produto;
	private Integer sku = 123;
	private Integer skuDiferenteProduto = 124;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		produto = createProduto();
	}
	
	@Test
	public void recuperarProdutoComSucesso() {
		when(repository.find(sku)).thenReturn(produto);
		Product produto = service.recupera(sku);
		Assert.assertEquals(produto.getSku(), sku);
	}
	
	@Test
	public void recuperarProdutoNaoEncontrado() {
		when(repository.find(sku)).thenReturn(produto);
		Product produto = service.recupera(null);
		Assert.assertNull(produto);
	}
	
	@Test
	public void salvaProdutoComSucesso() {
		when(repository.find(skuDiferenteProduto)).thenReturn(produto);
		service.salva(produto);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void salvaProdutoExistenteComErro() {
		when(repository.find(sku)).thenReturn(produto);
		service.salva(produto);
	}
	
	private Product createProduto() {
		return new Product(sku,"Test Product", createInventory());
	}

	private Inventory createInventory() {
		return new Inventory(createWarehouses());
	}

	private List<Warehouses> createWarehouses() {
		List<Warehouses> warehouses = new ArrayList<Warehouses>();
		warehouses.add(new Warehouses("SP", 12, "ECOMMERCE"));
		return warehouses;
	}
}
