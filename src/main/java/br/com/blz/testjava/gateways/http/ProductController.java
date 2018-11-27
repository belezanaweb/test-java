package br.com.blz.testjava.gateways.http;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.blz.testjava.gateways.http.request.ProductDTO;
import br.com.blz.testjava.usecases.ProductUseCase;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(path = "/product", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@RestController
public class ProductController {

  private final ProductUseCase productUseCase;

  @RequestMapping(value = "/{sku}", method = RequestMethod.GET)
  public ResponseEntity getProductBySku(@PathVariable("sku") @NotNull final int sku) {
    log.info("RECEIVED ON GET PRODUCT METHOD");
    return ResponseEntity.ok().body(productUseCase.findProductBySku(sku));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity createProduct(@RequestBody final ProductDTO productDTO) {
    log.info("RECEIVED ON CREATE PRODUCT METHOD");
    productUseCase.saveProduct(productDTO);
    return ResponseEntity.ok().body(productUseCase.findAllProducts());
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity updateProduct(@RequestBody final ProductDTO productDTO) {
    log.info("RECEIVED ON UPDATE PRODUCT METHOD");
    productUseCase.saveProduct(productDTO);
    return ResponseEntity.ok().body(productUseCase.findAllProducts());
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = "/{sku}", method = RequestMethod.DELETE)
  public void deleteProductBySku(@PathVariable("sku") @NotNull final int sku) {
    log.info("RECEIVED ON GET DELETE METHOD");
    productUseCase.deleteProductBySku(sku);
    log.info(sku + " SKU removed with success");
  }
}
