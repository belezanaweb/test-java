package br.com.blz.testjava;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
