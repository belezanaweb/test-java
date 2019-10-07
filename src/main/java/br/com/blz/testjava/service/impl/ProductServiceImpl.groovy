package br.com.blz.testjava.service.impl


import br.com.blz.testjava.model.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl implements br.com.blz.testjava.service.ProductService{

    @Autowired
    private br.com.blz.testjava.dao.ProductRepository productRepository;

    @Override
    Product save(Product product) {
        try {
            return productRepository.insert(product);
        } catch (DuplicateKeyException ex) {
            throw ex;
        }
    }

    @Override
    Product update(Product product) {

        productRepository.findById(product.sku).orElseThrow {-> new EmptyResultDataAccessException(1)};

        try{
            return productRepository.save(product);
        }catch (DuplicateKeyException ex ){
            throw ex;
        }

    }

    @Override
    Product findById(Long sku) {
        Product prodDb = productRepository.findById(sku).orElseThrow {-> new EmptyResultDataAccessException(1)};

        prodDb?.inventory?.quantity = 0;

        prodDb?.inventory?.warehouses?.forEach{
            prodDb?.inventory?.quantity += it.quantity;
        }

        prodDb.isMarketable = prodDb?.inventory?.quantity > 0;

        return prodDb;
    }

    @Override
    void deleteById(Long sku) {
        productRepository.deleteById(sku);
    }

}
