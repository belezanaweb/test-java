package br.com.blz.testjava.service;

import br.com.blz.testjava.exception.AlreadyExistsException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.ProductResponse;
import br.com.blz.testjava.service.utils.ObjectUtils;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

    private final List<ProductResponse> listMemorys = new ArrayList<ProductResponse>();

    @Override
    public ProductResponse findProduct(Integer id) {
        return listMemorys.stream()
            .filter(listMemory -> listMemory.getSku().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public void save(Product product) throws AlreadyExistsException {
        if (ObjectUtils.isContains(product, listMemorys)) {
            throw new AlreadyExistsException(product.getSku().toString());
        }
        listMemorys.add(ObjectUtils.buildProduct(product));
    }

    @Override
    public void update(Integer id,Product product) {
        if (ObjectUtils.isContains(product, listMemorys)) {
            var index = listMemorys.indexOf(findProduct(id));
            listMemorys.set(index, ObjectUtils.buildProduct(product));
        }
        listMemorys.add(ObjectUtils.buildProduct(product));
    }

    @Override
    public void remove(ProductResponse product) {
        listMemorys.remove(product);
    }
}
