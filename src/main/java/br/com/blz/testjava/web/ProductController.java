package br.com.blz.testjava.web;

import br.com.blz.testjava.core.usecases.CreateProductUseCase;
import br.com.blz.testjava.core.usecases.DeleteProductUseCase;
import br.com.blz.testjava.core.usecases.FindProductUseCase;
import br.com.blz.testjava.core.usecases.UpdateProductUseCase;
import br.com.blz.testjava.web.request.CreateProductRequestDTO;
import br.com.blz.testjava.web.request.UpdateProductRequestDTO;
import br.com.blz.testjava.web.response.FindProductResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "products", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final FindProductUseCase findProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestBody @Validated CreateProductRequestDTO requestDTO) {
        MDC.put("sku", requestDTO.getSku().toString());
        MDC.put("name", requestDTO.getName());
        log.info("Product_creating");

        createProductUseCase.execute(requestDTO.toDomain());

        log.info("Product_created");
        MDC.clear();
    }

    @DeleteMapping("/{sku}")
    @ResponseStatus(OK)
    public void delete(@PathVariable String sku) {
        MDC.put("sku", sku);
        log.info("Product_deleting");

        deleteProductUseCase.execute(sku);

        log.info("Product_deleted");
        MDC.clear();
    }

    @GetMapping("/{sku}")
    @ResponseStatus(OK)
    public FindProductResponseDTO find(@PathVariable String sku) {
        MDC.put("sku", sku);
        log.info("Product_finding");

        FindProductResponseDTO createProductUseCase = findProductUseCase.execute(sku);

        log.info("Product_found");
        MDC.clear();

        return createProductUseCase;
    }

    @PutMapping("/{sku}")
    @ResponseStatus(OK)
    public void update(@PathVariable String sku,
                       @RequestBody @Validated UpdateProductRequestDTO requestDTO) {

        MDC.put("sku", sku);
        MDC.put("name", requestDTO.getName());
        log.info("Product_updating");

        updateProductUseCase.execute(requestDTO.toDomain(), sku);

        log.info("Product_updating");
        MDC.clear();
    }

}
