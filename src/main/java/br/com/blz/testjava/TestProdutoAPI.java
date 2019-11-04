package br.com.blz.testjava;

import br.com.blz.testjava.controller.ProdutoController;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.service.ProdutoService;
import br.com.blz.testjava.util.Util;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ProdutoController.class)
public class TestProdutoAPI {
    

	@Autowired
    private MockMvc mockMvcTeste;

    @MockBean
    private ProdutoService service;

    @Test
    public void salvarProduto()
        throws Exception {

        Produto product = ApresentacaoProduto.criarProduto();
        doNothing().when(service).salvar(product);

        mockMvcTeste.perform(post(Util.API_V1_PRODUTOS )
            .content(new ObjectMapper().writeValueAsString(product))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.sku", is(product.getSku())));
    }
    @Test
    public void excluirProduto()
        throws Exception {
        Integer sku = 43264;
        doNothing().when(service).delete(sku);

        mockMvcTeste.perform(delete(Util.API_V1_PRODUTOS  + sku)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }
     
    @Test
    public void obterProduto() throws Exception {

        Produto product = ApresentacaoProduto.criarProduto();

        given(service.obterByChave(product.getSku())).willReturn(product);
        mockMvcTeste.perform(get(Util.API_V1_PRODUTOS + product.getSku())
            .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.name", is(product.getName())))
	        .andExpect(jsonPath("$.marketable", is(true)))
	        .andExpect(jsonPath("$.inventory.quantity", is(15)))
	        .andExpect(jsonPath("$.inventory.warehouses", hasSize(2)))
	        .andExpect(jsonPath("$.inventory.warehouses[0].locality", is("SP")))
	        .andExpect(jsonPath("$.inventory.warehouses[0].quantity", is(12)))
	        .andExpect(jsonPath("$.inventory.warehouses[0].type", is("ECOMMERCE")))
	        .andExpect(jsonPath("$.inventory.warehouses[1].quantity", is(3)));
    }

}