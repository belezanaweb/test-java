package test.blz.repository;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.blz.RedisUtils;
import test.blz.bean.ProductVO;

@Component
public class ProductCacheImpl implements ProductCache {

    private static final String PRODUCT = "PRODUCT";

    @Autowired
    private RedisUtils<ProductVO> redisUtils;

    @Override
    public void putOnCache (final ProductVO productVO) {
        redisUtils.putMap(PRODUCT, productVO.getSku(), productVO);
        redisUtils.setExpire(PRODUCT, 1, TimeUnit.DAYS);
    }

    @Override
    public ProductVO retrieveFromCache (final Long sku) {
        return redisUtils.getMapAsSingleEntry(PRODUCT, sku);
    }

    @Override
    public void removeFromCache (final Long sku) {
        redisUtils.removeFromCache(PRODUCT, sku);
    }

    @Override
    public void clearAll () {
        redisUtils.clearAll(PRODUCT);
    }
}
