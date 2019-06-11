package test.blz.resource;

import static java.text.MessageFormat.format;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import test.blz.bean.ProductCreateRequest;
import test.blz.bean.ProductResponse;
import test.blz.bean.ProductUpdateRequest;
import test.blz.bean.ProductVO;
import test.blz.exception.ProductAlreadyExistsException;
import test.blz.service.ProductService;

@RestController
public class ProductResource {

    private static final Logger log = LoggerFactory.getLogger(ProductResource.class);

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "register new product")
    public ResponseEntity<?> createProduct (@ApiParam("product payload") @RequestBody @Valid ProductCreateRequest request) {

        log.info(format("M=createProduct, request={0}", request));

        try {
            productService.createProduct(request.convertToVO());
        } catch (ProductAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getLocalizedMessage(), e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/product/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "find product")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "product does not exist") })
    public ResponseEntity<?> findProduct (@PathVariable("sku") Long sku) {

        log.info(format("M=findProduct, sku={0}", String.valueOf(sku)));

        final ProductVO productVO = productService.findProduct(sku);

        if (productVO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(ProductResponse.builder()
                .sku(productVO.getSku())
                .name(productVO.getName())
                .inventory(productVO.getInventory())
                .marketable(productVO.isMarketable())
                .build());
    }

    @PutMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "update product")
    public ResponseEntity<?> editProduct (@ApiParam("product payload") @RequestBody @Valid ProductUpdateRequest request) {

        log.info(format("M=editProduct, request={0}", request));

        productService.updateProduct(request.convertToVO());

        return ResponseEntity.ok(ProductResponse.builder()
                .sku(request.getSku())
                .name(request.getName())
                .inventory(request.getInventory())
                .marketable(request.isMarketable())
                .build());
    }

    @DeleteMapping(value = "/product/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "delete product")
    public ResponseEntity<?> deleteProduct (@PathVariable("sku") Long sku) {

        log.info(format("M=createProduct, request={0}", String.valueOf(sku)));

        productService.deleteProcuct(sku);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/clear", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "clearCache")
    public ResponseEntity<?> clearCache () {

        log.info("M=clearCache");

        productService.clearAllCache();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
