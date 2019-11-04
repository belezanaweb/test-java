package br.com.blz.testjava.http.converter;

import br.com.blz.testjava.base.interfaces.RequestConverter;
import br.com.blz.testjava.base.interfaces.ResponseConverter;
import br.com.blz.testjava.http.data.request.ProductRequest;
import br.com.blz.testjava.http.data.response.ProductResponse;
import br.com.blz.testjava.usecase.data.Product;

public interface ProductHttpConverter
    extends RequestConverter<ProductRequest, Product>, ResponseConverter<Product, ProductResponse> {
}
