package br.com.blz.testjava.service;

import br.com.blz.testjava.model.DefaultMessage;
import br.com.blz.testjava.model.SkuProduct;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class SkuProductService {

    List<SkuProduct> skuProducts = new ArrayList<>();

    public ResponseEntity<DefaultMessage> create(SkuProduct skuProduct) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if (skuProduct != null) {
            if (getSku(skuProduct.getSku()).isPresent()) {
                return new ResponseEntity<>(new DefaultMessage("This product already exists!"), headers, HttpStatus.CONFLICT);
            } else {
                skuProducts.add(skuProduct);
                return new ResponseEntity<>(new DefaultMessage("Success"), headers, HttpStatus.CREATED);

            }

        } else {
            return new ResponseEntity<>(new DefaultMessage("Malformed JSON"), headers, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<DefaultMessage> delete(long sku) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (getSku(sku).isPresent()) {
            skuProducts.remove(getSku(sku).get());

            return new ResponseEntity<>(new DefaultMessage("Success"), headers, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(new DefaultMessage("Sku not found!"), headers, HttpStatus.NO_CONTENT);
        }

    }

    public List<SkuProduct> listAll() {
        return skuProducts;

    }

    public ResponseEntity<SkuProduct> getSkuProduct(long sku) {
        Optional<SkuProduct> optSkuProduct = skuProducts.stream().filter(s -> s.getSku() == sku).findAny();
        if(optSkuProduct.isPresent()) {
            optSkuProduct.get().getInventory().setQuantity(
                    optSkuProduct.get().getInventory().getWarehouses().stream().mapToLong(w -> w.getQuantity()).sum()
            );
            optSkuProduct.get().setMarketable(optSkuProduct.get().getInventory().getQuantity() > 0);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return optSkuProduct.isPresent() ?
                new ResponseEntity<>(optSkuProduct.get(), HttpStatus.OK) :
                new ResponseEntity<>(new SkuProduct(), HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<DefaultMessage> updateProduct(SkuProduct skuProduct) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if (getSku(skuProduct.getSku()).isPresent()) {
            SkuProduct skuProductToUpdate = getSku(skuProduct.getSku()).get();
            skuProducts.remove(skuProductToUpdate);
            skuProducts.add(skuProduct);
            return new ResponseEntity<>(new DefaultMessage("Success"), headers, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(new DefaultMessage(""), headers, HttpStatus.NO_CONTENT);
        }

    }

    private Optional<SkuProduct> getSku(long sku) {
        return skuProducts.stream().filter(s -> s.getSku() == sku).findAny();
    }
}
