package br.com.blz.testjava.api.product.service;

import br.com.blz.testjava.api.mapper.MapperUtil;
import br.com.blz.testjava.api.product.controller.domain.ProductRequest;
import br.com.blz.testjava.api.product.controller.domain.ProductResponse;
import br.com.blz.testjava.api.product.entity.ProductEntity;
import br.com.blz.testjava.api.product.repository.ProductRepository;
import br.com.blz.testjava.common.exceptions.ProductAlreadyExistsException;
import br.com.blz.testjava.common.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository repository;
  private final MapperUtil mapperUtil;

  public ProductResponse create(final ProductRequest product) throws ProductAlreadyExistsException {
    if (repository.findBySku(product.getSku()).isPresent()) {
      throw new ProductAlreadyExistsException(
          "Dois produtos são considerados iguais se os seus skus forem iguais");
    }
    return mapperUtil.productEntitytoResponse(repository.save(mapperUtil.productRequestToEntity(product)));
  }

  public ProductResponse getProduct(final Integer sku) throws ProductNotFoundException {

    return mapperUtil.productEntitytoResponse(
        repository
            .findBySku(sku)
            .orElseThrow(
                () -> new ProductNotFoundException("Não foi Possivel encontrar o Produto")));
  }

  public ProductResponse update(final ProductRequest product) throws ProductNotFoundException {
    ProductEntity productEntity =
        repository
            .findBySku(product.getSku())
            .orElseThrow(
                () -> new ProductNotFoundException("Não foi Possivel encontrar o Produto"));

    return mapperUtil.productEntitytoResponse(
        repository.save(mapperUtil.productEntitytoEntityAtualizar(product, productEntity)));
  }

  public void delete(final Integer id) throws ProductNotFoundException {
    repository.delete(
        repository
            .findBySku(id)
            .orElseThrow(
                () -> new ProductNotFoundException("Não foi Possivel encontrar o Produto")));
  }
}
