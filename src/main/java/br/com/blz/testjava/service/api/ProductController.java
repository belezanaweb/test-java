package br.com.blz.testjava.service.api;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.data.ProductDataHandler;
import br.com.blz.testjava.service.data.exception.ProductSkuAlreadyExistsException;
import br.com.blz.testjava.service.data.exception.SkuNotProvidedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductDataHandler productDataHandler;

    /**
     * Recupera produto dado um sku
     *
     * @param sku id do produto
     * @return ResponseEntity
     */
    @GetMapping(value = "/{sku}", produces = "application/json")
    public ResponseEntity<String> getProduct(@PathVariable(value = "sku") Long sku) {

        AtomicReference<String> responseProductJson = new AtomicReference<>();
        AtomicReference<Boolean> isBadRequest = new AtomicReference<>();
        Optional<Product> optionalProduct = Optional.ofNullable(productDataHandler.getProduct(sku));
        optionalProduct.ifPresent(product -> {
            try {
                responseProductJson.set(objectMapper.writeValueAsString(product));
            } catch (JsonProcessingException e) {
                isBadRequest.set(Boolean.TRUE);
            }
        });

        if (Objects.nonNull(isBadRequest.get())) {
            return ResponseEntity.badRequest().body("Erro ao retornar Produto!");
        }
        return Objects.nonNull(responseProductJson.get()) ? ResponseEntity.ok(responseProductJson.get()) :
            ResponseEntity.notFound().build();
    }

    /**
     * Exclui produto dado um sku
     *
     * @param sku id do produto
     * @return ResponseEntity
     */
    @DeleteMapping("/{sku}")
    public ResponseEntity<String> removeProduct(@PathVariable(value = "sku") Long sku) {
        if (productDataHandler.removeProduct(sku)) {
            return ResponseEntity.ok(String.format("Produto %d foi removido", sku));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Atualiza produto dado um sku
     *
     * @param request  mensagem json do produto
     * @return ResponseEntity
     */
    @PutMapping(consumes = "application/json")
    public ResponseEntity<String> updateProduct(@RequestBody String request) {
        AtomicReference<String> productJson = new AtomicReference<>();
        Product product;
        try {
            productJson.set("Não foi possível alterar o produto ");
            product = objectMapper.readValue(request.getBytes(), Product.class);
            Optional.ofNullable(productDataHandler.updateProduct(product)).ifPresent(obj ->
                productJson.set(String.format(" Alterado com sucesso o produto com sku %d", product.getSku()))
            );
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(productJson.get());
        } catch (Exception ex) {
            ResponseEntity.badRequest().body(" <Erro ao atualizar produto> ");
        }
        return ResponseEntity.ok(productJson.get());
    }

    /**
     * Cria novo produto
     *
     * @param request mensagem json do produto
     * @return ResponseEntity
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> createProduct(@RequestBody String request) {

        AtomicReference<String> productJson = new AtomicReference<>();
        Product product;
        try {
            productJson.set("Não foi possível criar o produto");
            product = objectMapper.readValue(request.getBytes(), Product.class);
            Optional.ofNullable(productDataHandler.addProduct(product)).ifPresent(obj ->
                productJson.set(String.format(" Criado com sucesso o produto com sku %d", product.getSku()))
            );
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(productJson.get());
        } catch (ProductSkuAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(" Produto com sku ja existente!");
        } catch (SkuNotProvidedException e) {
            return ResponseEntity.badRequest().body("Sku do Produto não fornecido! ");
        } catch (Exception ex) {
            ResponseEntity.badRequest().body(" <Erro ao criar produto> ");
        }
        return ResponseEntity.ok(productJson.get());
    }
}
