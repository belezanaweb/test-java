package br.com.blz.testjava.controller;


import br.com.blz.testjava.dto.request.ProductRequestDTO;
import br.com.blz.testjava.dto.response.ProductResponseDTO;
import br.com.blz.testjava.dto.response.Response;
import br.com.blz.testjava.usecases.ProductUseCase;
import br.com.blz.testjava.utils.ConvertResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(
    value = "/api/product",
    produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_JSON_VALUE})
public class ProductImpController implements ProductApi {

    private ProductUseCase productUseCase;
    private ConvertResponse convertResponse;

    @Override
    public ResponseEntity<Response> create(@RequestBody ProductRequestDTO body) {
        log.info("Begin ProductImpController : create : body : " + body.toString());
        productUseCase.create(body);
        log.info("End ProductImpController : create : body : " + body.toString());
        return new ResponseEntity<>(convertResponse.create(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response> update(@PathVariable Integer sku, @RequestBody ProductRequestDTO body) {
        log.info("Begin ProductImpController : create : update : "+ " : sku : " + sku + " : body : " + body.toString());
        productUseCase.update(body, sku);
        log.info("End ProductImpController : create : update : "+ " : sku : " + sku + " : body : " + body.toString());
        return new ResponseEntity<>(convertResponse.update(), HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ProductResponseDTO> findBySku(@PathVariable Integer sku) {
        log.info("Begin ProductImpController : findBySku : "+ " : sku : " + sku);
        ProductResponseDTO productResponseDTO = productUseCase.getBySku(sku);
        log.info("Begin ProductImpController : findBySku : "+ " : sku : " + sku);
        return new ResponseEntity<ProductResponseDTO>(productResponseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Integer sku) {
        log.info("Begin ProductImpController : delete : "+ " : sku : " + sku);
        productUseCase.delete(sku);
        log.info("Begin ProductImpController : delete : "+ " : sku : " + sku);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
