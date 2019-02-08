package br.com.blz.testjava.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class RestHelper {
	
	protected String serverHost;
	protected String serverPort;
	
	protected ResponseEntity<String> sendGETRequest(String path){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(httpHeaders);
		return restTemplate.exchange(serverHost.concat(serverPort).concat(path), HttpMethod.GET,httpEntity,String.class);
	}
	
	protected ResponseEntity<String> sendPOSTRequest(String path,Object payload){
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(payload);
		return restTemplate.exchange(serverHost.concat(serverPort).concat(path), HttpMethod.POST,httpEntity,String.class);
	}
	
	protected ResponseEntity<String> sendPUTRequest(String path,Object payload){
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(payload);
		return restTemplate.exchange(serverHost.concat(serverPort).concat(path), HttpMethod.PUT,httpEntity,String.class);
	}
	

	protected void sendDeleteRequest(String path){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(serverHost.concat(serverPort).concat(path));
	}

}
