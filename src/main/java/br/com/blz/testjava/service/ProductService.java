package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.exception.CustomRuntimeException;
import br.com.blz.testjava.exception.ValidationMsg;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.support.CrudServiceImpl;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService extends CrudServiceImpl<ProductRepository, Product, Long, ProductDto> {

    public ProductService(ProductRepository repository, ModelMapper modelMapper) {
        super(repository, Product.class, ProductDto.class, modelMapper);
    }

    @Transactional
    @Override
    public ProductDto create(@NonNull ProductDto dto) {
        if (repository.existsById(dto.getId())) {
            throw new CustomRuntimeException(ValidationMsg.ENTITY_EXISTS, entityName, "SKU", dto.getId().toString());
        }
        return super.create(dto);
    }

}
