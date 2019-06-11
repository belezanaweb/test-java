package test.blz.service;

import static java.text.MessageFormat.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.blz.bean.ProductVO;
import test.blz.exception.ProductAlreadyExistsException;
import test.blz.repository.ProductCache;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductCache productCache;

    @Override
    public void createProduct (final ProductVO productVO) throws ProductAlreadyExistsException {
        log.info(format("M=createProduct, productVO={0}", productVO));

        if(findProduct(productVO.getSku()) != null){
            throw new ProductAlreadyExistsException();
        }

        updateProduct(productVO);
    }

    @Override
    public ProductVO findProduct (final Long sku) {
        log.info(format("M=findProduct, sku={0}", String.valueOf(sku)));
        return productCache.retrieveFromCache(sku);
    }

    @Override
    public void updateProduct (final ProductVO productVO) {
        log.info(format("M=updateProduct, productVO={0}", productVO));
        productCache.putOnCache(productVO);
    }

    @Override
    public void deleteProcuct (final Long sku) {
        log.info(format("M=deleteProcuct, sku={0}", String.valueOf(sku)));
        productCache.removeFromCache(sku);
    }

    @Override
    public void clearAllCache (){
        productCache.clearAll();
    }
}
