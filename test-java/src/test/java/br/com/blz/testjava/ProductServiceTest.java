package br.com.blz.testjava;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.blz.testjava.exception.ProductAlreadyRegisteredException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.WarehouseType;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;

@RunWith( MockitoJUnitRunner.class )
public class ProductServiceTest{

    @Mock
    private Warehouse warehouseOne;
    @Mock
    private Warehouse warehouseTwo;
    @Mock
    private Inventory inventoryOne;
    @Mock
    private Product productOne;
    @Mock
    private ProductRepository productRepository;
    
    @InjectMocks
    private ProductService productService;
    
    @Before
    public void setUp(){
        
    	when( warehouseOne.getLocality() ).thenReturn( "SP" );
        when( warehouseOne.getQuantity() ).thenReturn( 12 );
        when( warehouseOne.getType() ).thenReturn( WarehouseType.ECOMMERCE );
        
        when( warehouseTwo.getLocality() ).thenReturn( "Moema" );
        when( warehouseTwo.getQuantity() ).thenReturn( 3 );
        when( warehouseTwo.getType() ).thenReturn( WarehouseType.PHYSICAL_STORE );
        
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouseOne);
        warehouses.add(warehouseTwo);
        
        when( inventoryOne.getWarehouses() ).thenReturn( warehouses );
        when( inventoryOne.getQuantity() ).thenReturn( 15 );
        
        when( productOne.getSku() ).thenReturn( 43264 );
        when( productOne.getName() ).thenReturn( "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g" );
        when( productOne.getInventory() ).thenReturn( inventoryOne );
        when( productOne.isMarketable() ).thenReturn( true );
        
    }
    
    @Test
    public void shouldCreateProductSuccessfully() throws ProductAlreadyRegisteredException{
    	when( productService.saveProduct(productOne) ).thenReturn( 43264 );
    }
    
    @Test
    public void shouldCreateProductSuccessfullyWithIsMarketableEqualToNull() throws ProductAlreadyRegisteredException{
    	when( productOne.isMarketable() ).thenReturn( null );
    	when( productService.saveProduct(productOne) ).thenReturn( 43264 );
    }
    
    @Test
    public void shouldUpdateProductSuccessfully() throws ProductNotFoundException{
    	when( productService.updateProduct(productOne.getSku(), productOne ) ).thenReturn( 43264 );
    }
    
    @Test
    public void shouldDeleteProductSuccessfully() throws ProductNotFoundException{
    	when( productService.deleteProduct( productOne.getSku() ) ).thenReturn( 43264 );
    }
}

