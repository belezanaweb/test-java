package br.com.blz.testjava.service.impl;

import br.com.blz.testjava.domain.exception.ProductAlreadyExistsError;
import br.com.blz.testjava.domain.exception.ProductNotFoundError;
import br.com.blz.testjava.domain.model.Product;
import br.com.blz.testjava.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

  private static final Map<Long, Product> productStore = new ConcurrentHashMap<>();

  @Override
  public Product findBySku(Long sku) {
    Product product = productStore.get(sku);
    if (product == null) {
      throw new ProductNotFoundError("Product not found. SKU:" + sku);
    }
    return product;
  }

  @Override
  public List<Product> listAll() {
    return productStore.values().stream().collect(Collectors.toList());
  }

  @Override
  public Product create(Product product) {
    return productStore.compute(product.getSku(), (c, r) -> {
      if (r != null) {
        throw new ProductAlreadyExistsError("Product already exists! Sku: " + product.getSku());
      }
      return product;
    });
  }

  @Override
  public Product update(Product product) {
    findBySku(product.getSku());
    return productStore.put(product.getSku(), product);
  }

  @Override
  public void remove(Long sku) {
    productStore.remove(sku);
  }
}
