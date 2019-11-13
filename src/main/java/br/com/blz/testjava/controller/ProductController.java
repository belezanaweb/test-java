package br.com.blz.testjava.controller;

import br.com.blz.testjava.component.ProductConversorComponent;
import br.com.blz.testjava.contract.request.ProductRequest;
import br.com.blz.testjava.contract.response.ProductResponse;
import br.com.blz.testjava.contract.response.Response;
import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductConversorComponent productConversorComponent;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductConversorComponent productConversorComponent, ProductService productService) {
        this.productConversorComponent = productConversorComponent;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<?>> create(@Valid @RequestBody ProductRequest request, BindingResult result) throws URISyntaxException {
        log.info("Iniciando cadastro de produto com request: [{}]", request.toString());

        if (result.hasErrors()) {
            Response<ProductRequest> responseRequest = new Response<>();
            log.error("Erro validando dados de cadastro de produto: [{}]", result.getAllErrors());
            result.getAllErrors().forEach(error -> responseRequest.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(responseRequest);
        }

        Response<ProductResponse> response = new Response<>();
        Product product = productService.create(productConversorComponent.requestToEntityConverter(request));

        ProductResponse productResponse = productConversorComponent.entityToResponseConverter(product);
        response.setData(productResponse);
        log.error("Produto cadastrado com sucesso: [{}]", response.getData());
        return ResponseEntity.created(new URI("/products")).body(response);
    }

    @RequestMapping(value = "/{sku}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<?>> findProductBySku(@PathVariable("sku") Long sku) {
        log.error("Buscando produto com sku: [{}]", sku);
        ProductResponse productResponse = productConversorComponent.entityToResponseConverter(productService.findBySku(sku));
        Response<ProductResponse> response = new Response<>();
        response.setData(productResponse);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/{sku}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<?>> putArtist(@PathVariable("sku") Long sku,
                                                 @Valid @RequestBody ProductRequest request,
                                                 BindingResult result)  {

        log.info("Atualizando produto [{}] com sku: [{}]", request.toString(), sku);

        if (result.hasErrors()) {
            Response<ProductRequest> responseRequest = new Response<>();
            log.error("Erro validando dados de update de produto: [{}]", result.getAllErrors());
            result.getAllErrors().forEach(error -> responseRequest.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(responseRequest);
        }

        Response<ProductResponse> response = new Response<>();

        Product product = productService.update(sku, productConversorComponent.requestToEntityConverter(request));
        ProductResponse productResponse = productConversorComponent.entityToResponseConverter(product);
        response.setData(productResponse);
        log.error("Produto atualizado com sucesso: [{}]", response.getData());
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/{sku}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> remover(@PathVariable("sku") Long sku) {
        log.info("Iniciando Remoção de produto sku: [{}]", sku);
        productService.delete(sku);
        log.info("produto de sku: [{}] removido com sucesso", sku);
        return ResponseEntity.noContent().build();
    }
}
