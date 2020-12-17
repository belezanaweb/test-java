package br.com.blz.testjava.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.entity.Product;

@Component
public class ProductConverter {
    
    public ProductDTO toDto(Product entity) {
        ModelMapper modelMapper = new ModelMapper();
        return entity != null ? modelMapper.map(entity, ProductDTO.class) : null;
    }

    public Product toEntity(ProductDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return dto != null ? modelMapper.map(dto, Product.class) : null;
    }

}
