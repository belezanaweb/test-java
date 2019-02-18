package br.com.blz.testjava.resource;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Produto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ResourceImplTest {


    @Mock
    private ResourceImpl resource;

    @Mock
    private Inventory inventory;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        Produto segundoProduto = new Produto();
        segundoProduto.setId(2l);
        segundoProduto.setName("Maria teste");
        segundoProduto.setSku(43264l);
        segundoProduto.setInventory(inventory);
        resource.salvar(segundoProduto);
    }

    @Test(expected=Exception.class)
    public void CriarProdutoExistente(){

            Produto primeiroProduto = new Produto();
            primeiroProduto.setId(1l);
            primeiroProduto.setName("Joao teste");
            primeiroProduto.setSku(43264l);
            primeiroProduto.setInventory(inventory);
     resource.salvar(primeiroProduto);


    }
}
