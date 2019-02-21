package br.com.blz.testjava.resource;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.entity.Warehouses;
import br.com.blz.testjava.repository.ProdutoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class ResourceImplTest {

    @InjectMocks
    private ResourceImpl resourceImpl;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private Inventory inventory;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Mockito.mock(Warehouses.class);

    }

    @Test
    public void criarProdutoComSucesso(){
        Produto produto = new Produto();
        produto.setName("Maria teste");
        produto.setSku(43264l);
        produto.setInventory(inventory);
        produto.setMarketable(true);
        Mockito.when(produtoRepository.exists(produto.getSku())).thenReturn(false);

        final ResponseEntity<?> salvar = resourceImpl.salvar(produto);
        Assert.assertNotNull(salvar);
    }
    @Test
    public void editarProdutoComSucesso(){

        Produto produto = new Produto();
        produto.setName("Maria teste");
        produto.setSku(43264l);
        produto.setInventory(inventory);
        produto.setMarketable(true);

        final ResponseEntity<?> editar = resourceImpl.editar(produto);

        Assert.assertNotNull(editar);
    }
    @Test
    public void recuperarProduto(){
        Produto produto = new Produto();
        produto.setName("Maria teste");
        produto.setSku(43264l);
        produto.setInventory(inventory);
        produto.setMarketable(false);

        Mockito.when(produtoRepository.findBysku(produto.getSku())).thenReturn(Optional.of(produto));

        final ResponseEntity<?> recuperarProduto = resourceImpl.recuperar(produto.getSku());

        Assert.assertNotNull(recuperarProduto);
    }
}
