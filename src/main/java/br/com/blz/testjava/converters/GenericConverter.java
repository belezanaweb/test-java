package br.com.blz.testjava.converters;

import org.modelmapper.ModelMapper;

public class GenericConverter {
	ModelMapper mapper = new ModelMapper();
	
	public Object merge(Object source, Object destination) {
		mapper.map(source, destination);
		return destination;
	}

}
