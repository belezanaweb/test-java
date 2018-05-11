package br.com.blz.testjava;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		register(ProdutoWeb.class);
	}

}
