package br.com.blz.testjava;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJavaApplicationTests {

	@Test
	public void contextLoads() {
	}
	 @Autowired
	    private ProdutoRepository produtoRepository;
	    
 /*   @Test
    public void createUsers() {
    	
    	Produto produto = new Produto();
    	produto.setSku(43263);
    	produto.setName("LOREAL");
    	produto.setInventory(0);
    	produto.setMarketable(true);
    	
    	produtoRepository.save(produto);
        List<Produto> produtos = (List<Produto>) produtoRepository.findAll();
        for(Produto str : produtos){
        	System.out.println(str.getSku());
        	System.out.println(str.getName());
        	System.out.println(str.getInventory());
        	System.out.println(str.isMarketable());
        }
        
        
        
//        assertThat(produtos.size()).isEqualTo(1);
    }    
*/
/*    @Test
    public void deleteProduct() {
    	Produto produto = produtoRepository.findById(4);
    	if(produto != null){
    		produtoRepository.delete(produto.getId());
    	}
    	System.out.println("O produto Id: [4] foi removido");
    }*/
    
  /*  @Test
    public void updateProduct() {
    	Produto produto = produtoRepository.findById(3);
    	if(produto != null){
    		produto.setName("PRODUTO");
    		produtoRepository.save(produto);
    		System.out.println("O produto Id: [2] foi atualizado");
    	}else{
			Produto novoProduto = new Produto();
			novoProduto.setSku(43273);
			novoProduto.setName("PRODUTO 10");
			novoProduto.setInventory(0);
			novoProduto.setMarketable(false);
    		produtoRepository.save(novoProduto);
    		System.out.println("O produto Id: [3] foi atualizado");
    	}
    }*/
    
}
