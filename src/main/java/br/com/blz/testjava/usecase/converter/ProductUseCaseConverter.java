package br.com.blz.testjava.usecase.converter;

import br.com.blz.testjava.base.interfaces.RequestConverter;
import br.com.blz.testjava.base.interfaces.ResponseConverter;
import br.com.blz.testjava.gateway.documents.ProductDocument;
import br.com.blz.testjava.usecase.data.Product;

public interface ProductUseCaseConverter
    extends RequestConverter<Product, ProductDocument>, ResponseConverter<ProductDocument, Product> {
}
