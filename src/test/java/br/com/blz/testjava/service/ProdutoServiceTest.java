package br.com.blz.testjava.service;

import br.com.blz.testjava.Repository.WarehouseRepository;
import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.entity.ProdutoExistenteException;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.entity.dto.ProdutoEntradaDTO;
import br.com.blz.testjava.entity.dto.ProdutoRetornoDTO;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class ProdutoServiceTest {

    @Mock
    WarehouseRepository repository;
    @InjectMocks
    ProdutoService service = new ProdutoService();
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.LENIENT);

    @Test
    public void salvarEBuscarProdutoTest() throws IOException {

        when(repository.findWarehouseByUf("SP")).thenReturn(new Warehouse("SP", "ECOMMERCE"));
        when(repository.findWarehouseByUf("MOEMA")).thenReturn(new Warehouse("MOEMA", "PHYSICAL_STORE"));
        when(repository.list()).thenReturn(Collections.unmodifiableSet(criarWareHouses()));

        ProdutoEntradaDTO entradaDTO = new Gson().fromJson(carregarProdutoEntradaDTO(), ProdutoEntradaDTO.class);
        ProdutoRetornoDTO retornoDTO = new Gson().fromJson(carregarProdutoRetornoDTO(), ProdutoRetornoDTO.class);

        service.save(entradaDTO);

        Assert.assertEquals(new Gson().toJson(service.getProduto(entradaDTO.getSku())), new Gson().toJson(retornoDTO));


    }

    @Test(expected = ProdutoExistenteException.class)
    public void salvarEBuscarProdutoTestErro() throws IOException {

        List<Warehouse> warehouses = criarListaWareHouses();

        when(repository.findWarehouseByUf("SP")).thenReturn(warehouses.get(0));
        when(repository.findWarehouseByUf("MOEMA")).thenReturn(warehouses.get(1));
        when(repository.list()).thenReturn(Collections.unmodifiableSet(criarWareHouses()));

        ProdutoEntradaDTO entradaDTO = new Gson().fromJson(carregarProdutoEntradaDTO(), ProdutoEntradaDTO.class);

        service.save(entradaDTO);
    }

    private String carregarProdutoEntradaDTO() throws IOException {
        File file = ResourceUtils.getFile("classpath:dtoEntrada.txt");
        return new String(Files.readAllBytes(file.toPath()));
    }

    private String carregarProdutoRetornoDTO() throws IOException {
        File file = ResourceUtils.getFile("classpath:dtoRetorno.txt");
        return new String(Files.readAllBytes(file.toPath()));
    }

    private Set<Warehouse> criarWareHouses(){
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setType("ECOMMERCE");
        warehouse.saveProduto(new Produto(12345L, "Produto Teste", 12));

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setLocality("MOEMA");
        warehouse2.setType("PHYSICAL_STORE");
        warehouse2.saveProduto(new Produto(12345L, "Produto Teste", 3));

        return Sets.newSet(warehouse, warehouse2);

    }

    private List<Warehouse> criarListaWareHouses(){
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setType("ECOMMERCE");
        warehouse.saveProduto(new Produto(12345L, "Produto Teste", 12));

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setLocality("MOEMA");
        warehouse2.setType("PHYSICAL_STORE");
        warehouse2.saveProduto(new Produto(12345L, "Produto Teste", 3));

        return Arrays.asList(warehouse, warehouse2);

    }

}
