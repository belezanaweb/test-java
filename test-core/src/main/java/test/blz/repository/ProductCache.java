package test.blz.repository;

import test.blz.bean.ProductVO;

public interface ProductCache {

    void putOnCache(ProductVO productVO);

    ProductVO retrieveFromCache(Long sku);

    void removeFromCache(Long sku);

    void clearAll();

}
