package br.com.blz.testjava.controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.exception.SkuAlreadyExistsException;
import br.com.blz.testjava.exception.SkuNotFoundException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.ProductValidator;
import br.com.blz.testjava.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
@Path("/sku")
@Produces(MediaType.APPLICATION_JSON)
public class SkuController {

	private final SkuService service;
	private final ProductValidator validator;

	@Autowired
	public SkuController(SkuService service, ProductValidator validator) {
		this.service = service;
		this.validator = validator;
	}

	@POST
    @ApiOperation(value = "Endpoint efetua criação do produto informado",
            produces = MediaType.APPLICATION_JSON,
            response = String.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Criação efetuada com sucesso.",response = String.class),
            @ApiResponse(code = 417, message = "Formato de documento incorreto."),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor.")
    })
    public ResponseEntity<?> create(Product product) throws SkuAlreadyExistsException {
		validator.validate(product);
		service.create(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@PUT
    @ApiOperation(value = "Endpoint que executa atualizacao do produto informado",
            produces = MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualização efetuada com sucesso."),
            @ApiResponse(code = 417, message = "Formato de documento incorreto."),
            @ApiResponse(code = 404, message = "Sku informado não encontrado."),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor.")
    })
    public ResponseEntity<?> update(Product product) throws SkuNotFoundException {
		validator.validate(product);
		service.update(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@GET
    @Path("/{skuId}")
    @ApiOperation(value = "Endpoint que retorna o produto informado ou erro caso o mesmo não exista.",
            produces = MediaType.APPLICATION_JSON,
            response = String.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta efetuada com sucesso.",response = String.class),
            @ApiResponse(code = 417, message = "Formato de documento incorreto."),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor.")
    })
    public ResponseEntity<Product> read(@ApiParam(required = true) @HeaderParam(value = "skuId") Long skuId) throws SkuNotFoundException {
		return new ResponseEntity<>(service.read(skuId), HttpStatus.OK);
    }

	@DELETE
    @Path("/{skuId}")
    @ApiOperation(value = "Endpoint que executa deleção do produto informado.",
            produces = MediaType.APPLICATION_JSON,
            response = String.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleção efetuada com sucesso.",response = String.class),
            @ApiResponse(code = 417, message = "Formato de documento incorreto."),
            @ApiResponse(code = 500, message = "Ocorreu um erro no servidor.")
    })
    public ResponseEntity<?> remove(@ApiParam(required = true) @HeaderParam(value = "skuId") Long skuId) throws SkuNotFoundException {
		service.remove(skuId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
