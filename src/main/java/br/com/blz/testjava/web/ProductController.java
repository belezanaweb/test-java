package br.com.blz.testjava.web;


import br.com.blz.testjava.data.dto.ProductDTO;
import br.com.blz.testjava.data.response.ProductResponse;
import br.com.blz.testjava.exception.BoticarioInternalErrorException;
import br.com.blz.testjava.exception.BoticarioNotFoundException;
import br.com.blz.testjava.exception.BoticarioTimeoutException;
import br.com.blz.testjava.exception.BoticarionBadRequestException;
import br.com.blz.testjava.service.ProductService;
import br.com.blz.testjava.utils.ApiErrors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/boticario/api/v1/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> create(@RequestBody @Validated ProductDTO productDTO) {

        try {
            return ResponseEntity.ok(productService.createProduct(productDTO));
        } catch (BoticarioTimeoutException cause) {
            return ApiErrors.timeout(cause.getMessage());

        } catch (BoticarionBadRequestException cause) {
            return ApiErrors.badRequest(cause.getMessage());

        } catch (BoticarioInternalErrorException cause) {
            return ApiErrors.InternalError(cause.getMessage());
        }
    }

    @PutMapping(value = "/update/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> update(@RequestBody @Validated ProductDTO productDTO, @PathVariable final String sku) {

        try {
            return ResponseEntity.ok(productService.updateProduct(sku, productDTO));
        } catch (BoticarioTimeoutException cause) {
            return ApiErrors.timeout(cause.getMessage());

        } catch (BoticarionBadRequestException cause) {
            return ApiErrors.badRequest(cause.getMessage());

        } catch (BoticarioInternalErrorException cause) {
            return ApiErrors.InternalError(cause.getMessage());
        } catch (BoticarioNotFoundException cause) {
            return ApiErrors.notFound(cause.getMessage());
        }
    }

    @GetMapping(value = "/find/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> get(@PathVariable final String sku) {

        try {
            return ResponseEntity.ok(productService.findProduct(sku));
        } catch (BoticarioTimeoutException cause) {
            return ApiErrors.timeout(cause.getMessage());

        } catch (BoticarionBadRequestException cause) {
            return ApiErrors.badRequest(cause.getMessage());

        } catch (BoticarioInternalErrorException cause) {
            return ApiErrors.InternalError(cause.getMessage());
        }
        catch (BoticarioNotFoundException cause) {
            return ApiErrors.notFound(cause.getMessage());
        }
    }


    @DeleteMapping(value = "/delete/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable String sku) {

        try {
            productService.deleteProduct(sku);
            return ResponseEntity.ok().build();
        } catch (BoticarioTimeoutException cause) {
            return ApiErrors.timeout(cause.getMessage());

        } catch (BoticarionBadRequestException cause) {
            return ApiErrors.badRequest(cause.getMessage());

        } catch (BoticarioInternalErrorException cause) {
            return ApiErrors.InternalError(cause.getMessage());
        }
        catch (BoticarioNotFoundException cause) {
            return ApiErrors.notFound(cause.getMessage());
        }
    }
}
