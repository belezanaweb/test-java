package br.com.blz.testjava;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.blz.service.SkuService;

public class AbstractControllerTest {
	
	protected static final Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
	
	@Autowired
	protected MockMvc mvc;
	
	@Autowired
	protected SkuService skuService;
	
	public AbstractControllerTest() {}
	
	public <T> ResultActions executePostRequest(String url, T requestObject) throws Exception {
		//return mvc.perform(post(url).with(csrf()).contentType(MediaType.APPLICATION_JSON_VALUE).content(toJson(requestObject)));
		return mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(toJson(requestObject)));
	}
	
	public ResultActions executeGetRequest(String url) throws Exception{
		return mvc.perform(get(url));
	}
	
	public String toJson(Object object){
		String json = gson.toJson(object);
		return json;
	}
	
	/*
	public void populateDependencies(AbstractControllerTest abstractControllerTest) {
		this.mvc = abstractControllerTest.mvc;
		this.userDao = abstractControllerTest.userDao;
		this.quotationDao = abstractControllerTest.quotationDao;
		this.currencyService = abstractControllerTest.currencyService;
		this.quotationService = abstractControllerTest.quotationService;
		this.userService = abstractControllerTest.userService;
	}
	*/
}