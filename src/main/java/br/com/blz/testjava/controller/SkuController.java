package br.com.blz.testjava.controller;

import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sku")
public class SkuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkuController.class);

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createOrUpdate(@RequestBody @Validated ProductDto productDto) {
        LOGGER.info("SkuController:Recebedo requisição criação/atualização. sku:{}", productDto.getSku());

        Object body = "Produto criado ou autalizado com sucesso";
        HttpStatus status = HttpStatus.OK;

        try {
            LOGGER.info("SkuController:Solicitando criação/atualização. sku:{}", productDto.getSku());
            productService.createOrUpdate(productDto);
            LOGGER.info("SkuController:Criação/Atualização com sucesso. sku:{}", productDto.getSku());
        } catch (Exception e) {
            body = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            LOGGER.info("SkuController:Falha na Criação/Atualização. sku:{}", productDto.getSku());
        }

        return new ResponseEntity(body, status);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody @Validated ProductDto productDto) {
        LOGGER.info("SkuController:Recebedo requisição criação. sku:{}", productDto.getSku());

        Object body = "Produto criado com sucesso";
        HttpStatus status = HttpStatus.OK;

        try {
            LOGGER.info("SkuController:Solicitando criação. sku:{}", productDto.getSku());
            productService.create(productDto);
            LOGGER.info("SkuController:Criação/Atualização com sucesso. sku:{}", productDto.getSku());
        } catch (Exception e) {
            body = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            LOGGER.info("SkuController:Falha na Criação. sku:{}", productDto.getSku());
        }

        return new ResponseEntity(body, status);
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<?> delete(@PathVariable("sku") Long sku) {
        LOGGER.info("SkuController:Recebedo requisição delete. sku:{}", sku);

        Object body = "Sku " + sku;
        HttpStatus status = HttpStatus.OK;

        try {
            LOGGER.info("SkuController:Solicitando delete. sku:{}", sku);
            productService.delete(sku);
            LOGGER.info("SkuController:Delete com sucesso. sku:{}", sku);
        } catch (Exception e) {
            body = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            LOGGER.info("SkuController:Falha na deleção. sku:{}", sku);
        }

        return new ResponseEntity(body, status);

    }

    @GetMapping("/{sku}")
    public ResponseEntity<?> retrieve(@PathVariable("sku") Long sku) {
        LOGGER.info("SkuController:Buscando sku:{}", sku);

        Object body;
        HttpStatus status = HttpStatus.OK;

        try {
            LOGGER.info("SkuController:Buscando. sku:{}", sku);
            body = productService.retriver(sku);
            LOGGER.info("SkuController:Sku recuperado com sucesso. sku:{}", sku);
        } catch (Exception e) {
            body = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            LOGGER.info("SkuController:Falha na recuperação. sku:{}", sku);
        }

        return new ResponseEntity(body, status);
    }
}
