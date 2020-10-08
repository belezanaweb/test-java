package br.com.blz.testjava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.validator.ProductValidator;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {
	
	@Autowired
	private ProductValidator productValidator;
	
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		validatingListener.addValidator("beforeCreate", productValidator);
	}
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Product.class);
	}
	
}
