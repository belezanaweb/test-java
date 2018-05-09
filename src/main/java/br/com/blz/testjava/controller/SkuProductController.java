package br.com.blz.testjava.controller;

import br.com.blz.testjava.model.DefaultMessage;
import br.com.blz.testjava.model.SkuProduct;
import br.com.blz.testjava.service.SkuProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkuProductController {

    @Autowired
    SkuProductService skuProductService;

    @RequestMapping(value = "/sku/product", method = RequestMethod.POST)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DefaultMessage> createProduct(@RequestBody final SkuProduct skuProduct) {
        return skuProductService.create(skuProduct);
    }

    // Just verify if my tests are ok
    @RequestMapping(value = "/sku/product/listAll", method = RequestMethod.GET)
    @ResponseBody
    public List<SkuProduct> listAll() {
        return skuProductService.listAll();
    }

    @RequestMapping(value = "/sku/product", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SkuProduct> getBySku(@RequestParam("sku") final String sku) {

        return skuProductService.getSkuProduct(Long.valueOf(sku));
    }

    @RequestMapping(value = "/sku/product", method = RequestMethod.DELETE)
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DefaultMessage> delete(@RequestParam("sku") final String sku) {

        return skuProductService.delete(Long.valueOf(sku));
    }

    @RequestMapping(value = "/sku/product", method = RequestMethod.PUT)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DefaultMessage> update(@RequestBody final SkuProduct skuProduct) {
        return skuProductService.updateProduct(skuProduct);
    }
}
