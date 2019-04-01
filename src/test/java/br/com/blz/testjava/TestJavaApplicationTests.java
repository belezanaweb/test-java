package br.com.blz.testjava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.blz.testjava.business.ProductService;
import br.com.blz.testjava.controller.ProductController;
import br.com.blz.testjava.model.vo.ProductVO;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class TestJavaApplicationTests {

	private static final String URL = "http://localhost:8191/products";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductService pService;
	
	@Test
	public void getProductBySku200() throws Exception {
		ProductVO mockProduct = new ProductVO(456L, "TEST", null, false);
		
		Mockito.when(
				pService.getProductBySku(Mockito.anyLong())).thenReturn(mockProduct);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL+"/456").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();
		
		StringBuilder expected = new StringBuilder(); 
		expected.append("{");
		expected.append("sku: 456,");
		expected.append("name: TEST,");
		expected.append("inventory: null,");
		expected.append("isMarketable: false");
		expected.append("}");

		JSONAssert.assertEquals(expected.toString(), result.getResponse()
				.getContentAsString(), false);
	}

}
