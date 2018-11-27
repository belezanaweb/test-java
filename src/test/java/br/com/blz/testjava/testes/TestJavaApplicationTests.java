package br.com.blz.testjava.testes;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.controles.ProdutoControle;
import br.com.blz.testjava.entidades.db.Inventory;
import br.com.blz.testjava.entidades.db.Produto;
import br.com.blz.testjava.entidades.db.Warehouse;

import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("testes")
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
//@Ignore
public class TestJavaApplicationTests {
	
	@Autowired
	private ProdutoControle produtoControle;
	
	@Test
	public void testarProdutos() {
		Produto produto;
		Inventory inventory;
		Warehouse warehouse1;
		Warehouse warehouse2;
		
		// --- 1
		produto = new Produto();
		produto.setName("Produto um");
		inventory = new Inventory();
		produto.setInventory(inventory);
		warehouse1 = new Warehouse();
		warehouse1.setType("tipo-1");
		warehouse1.setLocality("SP");
		warehouse1.setQuantity(12);
		warehouse2 = new Warehouse();
		warehouse2.setType("tipo-2");
		warehouse2.setLocality("RJ");
		warehouse2.setQuantity(4);
		inventory.setWarehouses(Arrays.asList(warehouse1,warehouse2));
		
		produtoControle.gravar(produto);
		log.info("Foi gravado o Produdo: {}", produto);
		assertTrue("Id/Sku do Produto não foi alterado após enviar para o Controle gravar!", produto.getSku() > 0 );
		assertTrue("Id do Inventory não foi alterado após enviar para o Controle gravar!", produto.getInventory().getId() > 0 );
		
		assertTrue("Id do primeiro Warehouse não foi alterado após enviar para o Controle gravar!", 
			produto.getInventory().getWarehouses().get(0).getId() > 0 );
		assertTrue("Id do segundo Warehouse não foi alterado após enviar para o Controle gravar!", 
			produto.getInventory().getWarehouses().get(1).getId() > 0 );
			
		assertTrue("O cálculo de 'quantity' está errado!", produto.getInventory().getQuantity() == 16 );
		assertTrue("O valor de 'marketable' está errado!", produto.isMarketable() == true );
		
		// --- Ler todos
		List<Produto> itens = produtoControle.buscar();
		log.info("Buscamos os Produdos: {}", itens);
		//log.info("Buscamos os Produdos: {}", itens.get(0).getInventory());
		assertTrue("Os Produtos não foram carregados!", itens.size() > 0 );
		assertTrue("Os Inventories não foram carregados!", itens.get(0).getInventory() != null );
		assertTrue("Os Warehouses não foram carregados!", itens.get(0).getInventory().getWarehouses() != null
			&& itens.get(0).getInventory().getWarehouses().size() > 0 );
		assertTrue("O cálculo de 'quantity' está errado!", itens.get(0).getInventory().getQuantity() == 16 );
		assertTrue("O valor de 'marketable' está errado!", itens.get(0).isMarketable() == true );
			
		// --- Ler um
		Produto item = produtoControle.buscar(1);
		log.info("Buscamos o Produdo de Id/Sku 1: {}", item);
		assertTrue("Os Produtos não foram carregados!", item != null );
		
		// --- Atualizar
		String novoNome = "Outro nome";
		produto = new Produto();
		produto.setSku(1);
		produto.setName(novoNome);
		inventory = new Inventory();
		produto.setInventory(inventory);
		warehouse1 = new Warehouse();
		warehouse1.setType("tipo-1");
		warehouse1.setLocality("SP");
		warehouse1.setQuantity(12);
		inventory.setWarehouses(Arrays.asList(warehouse1));
		produtoControle.atualizar(produto);
		
		Produto item2 = produtoControle.buscar(1); // buscar o item que atualizamos
		log.info("Buscamos o Produdo que atualizamos: {}", item2);
		assertTrue("O Produto não foi atualizado!", item2.getName().equals(novoNome) );
		
		
		// --- Deletar
		produtoControle.deletar(1); // testar principalmente este método pois iremos receber um ID nas bordas
		List<Produto> itens2 = produtoControle.buscar();
		log.info("Buscamos os Produdos: {}", itens2);
		assertTrue("O Produto não foi deletado!", itens2.size() == 0 );
		
	}

}
