package br.com.blz.testjava.controller.support;

import br.com.blz.testjava.dto.support.ValidationGroups;
import br.com.blz.testjava.service.support.CrudService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.groups.Default;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.NonNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public abstract class CrudController<S extends CrudService<I, D>, I, D> {

    protected final S service;

    public CrudController(@NonNull S service) {
        this.service = service;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<D> create(@Validated({Default.class, ValidationGroups.Create.class}) @RequestBody D dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<D> update(@PathVariable I id, @Validated({Default.class, ValidationGroups.Update.class}) @RequestBody D dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<D> getById(@PathVariable I id) {
        return ResponseEntity.of(service.findById(id));
    }

    @ApiOperation(value = "Retrieve one page with registers")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful request!"),
        @ApiResponse(code = 403, message = "Forbidden request"),
        @ApiResponse(code = 500, message = "Failed request!"),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<D>> getAll(@PageableDefault(sort = "id") Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable I id) {
        return ResponseEntity.of(service.deleteById(id));
    }

}
