package br.com.blz.testjava.gateway.repository.impl;

import br.com.blz.testjava.base.exceptions.DocumentAlreadyExistsException;
import br.com.blz.testjava.base.exceptions.DocumentNotExistsException;
import br.com.blz.testjava.gateway.documents.ProductDocument;
import br.com.blz.testjava.gateway.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@ApplicationScope
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private Map<Long, ProductDocument> cache;

    @Override
    public ProductDocument getById(Long sku) {

        return getCache().get(sku);
    }

    @Override
    public ProductDocument create(ProductDocument productDocument) {
        Long sku = productDocument.getSku();
        ProductDocument oldDocument = getById(sku);
        if(isNull(oldDocument)){
            getCache().put(sku, productDocument);
            return  productDocument;
        }
        throw new DocumentAlreadyExistsException(String.valueOf(sku));
    }

    @Override
    public ProductDocument update(ProductDocument productDocument) {
        Long sku = productDocument.getSku();
        ProductDocument oldDocument = getById(sku);
        if(nonNull(oldDocument)){
            getCache().put(sku, productDocument);
            return  productDocument;
        }
        throw new DocumentNotExistsException(String.valueOf(sku));

    }

    @Override
    public void delete(Long sku) {
        ProductDocument oldDocument = getById(sku);
        if(isNull(oldDocument)){
            throw new DocumentNotExistsException(String.valueOf(sku));
        }
        getCache().remove(sku);

    }

    private Map<Long, ProductDocument> getCache(){
        if (isNull(this.cache)){
            this.cache = new HashMap<>();
        }
        return this.cache;
    }

}
