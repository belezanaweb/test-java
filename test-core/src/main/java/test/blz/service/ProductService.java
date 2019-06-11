package test.blz.service;

import test.blz.bean.ProductVO;
import test.blz.exception.ProductAlreadyExistsException;

public interface ProductService {

    void createProduct (ProductVO productVO) throws ProductAlreadyExistsException;

    ProductVO findProduct (Long sku);

    void updateProduct (ProductVO productVO);

    void deleteProcuct (Long sku);

    void clearAllCache ();
}
