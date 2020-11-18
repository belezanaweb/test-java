package br.com.blz.testjava.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.business.domain.GlobalMessage;
import br.com.blz.testjava.business.domain.Product;
import br.com.blz.testjava.business.service.ProductService;
import br.com.blz.testjava.common.exception.BusinessException;
import br.com.blz.testjava.common.exception.GlobalExceptionHandler;

import br.com.blz.testjava.mapper.ProductMapper;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class ProductResourceTest {

	@InjectMocks
	private ProductResource productResource;
	
	@Mock
	private ProductService productService;
	
	private MockMvc mockMvc;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        
        mockMvc = MockMvcBuilders.standaloneSetup(this.productResource)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        
        FixtureFactoryLoader.loadTemplates("br.com.blz");
    }
	
	@Test
	public void shouldSaveProduct() throws Exception {
		Product product = Fixture.from(Product.class).gimme("valid-product");
		
		Mockito.when(productService.save(Mockito.any(Product.class))).thenReturn(product);
		
		this.mockMvc.perform(post("/api/v1/produtos")
				.content(asJsonString(ProductMapper.toDto(product)))
				.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("result.sku").value("1987"))
                .andExpect(jsonPath("result.name").value("esmalte"))
                .andExpect(jsonPath("$.messages[0].code").value("1"))
                .andExpect(jsonPath("$.messages[0].description").value("criado com sucesso."));
	}
	
	@Test
	public void shouldValidateWhenProductAlreadyExist() throws Exception {
		Product product = Fixture.from(Product.class).gimme("valid-product");
		
		BusinessException exception = new BusinessException(GlobalMessage.PRODUCT_DUPLICATED);
		
		Mockito.when(productService.save(Mockito.any(Product.class))).thenThrow(exception);
		
		this.mockMvc.perform(post("/api/v1/produtos")
				.content(asJsonString(ProductMapper.toDto(product)))
				.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages[0].code").value("-3"))
                .andExpect(jsonPath("$.messages[0].description").value("The product already exist."));
	}
	
	@Test
	public void shouldUpdateProduct() throws Exception {
		Product product = Fixture.from(Product.class).gimme("valid-product");
		Long sku = 1987l;
		
		Mockito.when(productService.update(Mockito.any(Product.class), Mockito.any(Long.class))).thenReturn(product);
		
		this.mockMvc.perform(put("/api/v1/produtos/{sku}", sku)
				.content(asJsonString(ProductMapper.toDto(product)))
				.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("result.sku").value("1987"))
                .andExpect(jsonPath("result.name").value("esmalte"))
                .andExpect(jsonPath("$.messages[0].code").value("0"))
                .andExpect(jsonPath("$.messages[0].description").value("Operacao realizada com sucesso."));
	}
	
	@Test
	public void shouldValidateWhenIdentificationsAreDifferent() throws Exception {
		Product product = Fixture.from(Product.class).gimme("valid-product");
		Long sku = 1444l;
		
		BusinessException exception = new BusinessException(GlobalMessage.DIFFERENT_IDS);
		
		Mockito.when(productService.update(Mockito.any(Product.class), Mockito.anyLong())).thenThrow(exception);
		
		this.mockMvc.perform(put("/api/v1/produtos/{sku}", sku)
				.content(asJsonString(ProductMapper.toDto(product)))
				.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages[0].code").value("-3"))
                .andExpect(jsonPath("$.messages[0].description").value("The identifications are differents"));
	}
	
	@Test
	public void shouldFindProduct() throws Exception {
		Product product = Fixture.from(Product.class).gimme("valid-product-all");
		
		Long sku = 1987l;
		
		Mockito.when(productService.findBySku(Mockito.anyLong())).thenReturn(Optional.of(product));
		
		this.mockMvc.perform(get("/api/v1/produtos/{sku}", sku)
				.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("result.sku").value("1987"))
                .andExpect(jsonPath("result.name").value("esmalte"))
                .andExpect(jsonPath("result.marketable").value(true))
                .andExpect(jsonPath("result.inventory.quantity").value("5"))
                .andExpect(jsonPath("result.inventory.warehouses[0].locality").value("ribeirao"))
                .andExpect(jsonPath("result.inventory.warehouses[0].quantity").value("5"))
                .andExpect(jsonPath("result.inventory.warehouses[0].type").value("ECOMMERCE"))
                .andExpect(jsonPath("$.messages[0].code").value("0"))
                .andExpect(jsonPath("$.messages[0].description").value("Operacao realizada com sucesso."));
	}
	
	@Test
	public void shouldValidateWhenProductNotFound() throws Exception {
		Mockito.when(productService.findBySku(Mockito.anyLong())).thenReturn(Optional.empty());
		Long sku = 1987l;
		
		this.mockMvc.perform(get("/api/v1/produtos/{sku}", sku)
				.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.messages[0].code").value("3"))
                .andExpect(jsonPath("$.messages[0].description").value("Não existem resultados a serem exibidos para a pesquisa informada."));
	}
	
	public String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
