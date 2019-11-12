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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("Iniciando cadastro de artista com request: [{}]", request.toString());

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
}
