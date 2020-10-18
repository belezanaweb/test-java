package br.com.blz.testjava.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean 
	public ModelMapper getMapper () {
		ModelMapper mapper = new ModelMapper();
		return mapper;
	}
}
