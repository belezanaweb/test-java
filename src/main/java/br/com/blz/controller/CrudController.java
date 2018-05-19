package br.com.blz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.blz.exception.CrudException;
import br.com.blz.service.AbstractService;

public abstract class CrudController<T> {
	
	abstract AbstractService<T> abstractService();
	
	@RequestMapping(value="/create",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code=HttpStatus.OK)
	public void create(@RequestBody T object) throws CrudException {
		this.abstractService().create(object);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code=HttpStatus.OK)
	public void update(@RequestBody T object) {
		this.abstractService().update(object);
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	@ResponseStatus(code=HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		this.abstractService().delete(id);
	}
	
	@RequestMapping(value="/get/{id}",method=RequestMethod.GET)
	public ResponseEntity<ModelMap> byId(@PathVariable Long id) {
		ModelMap model = new ModelMap();
		T object = this.abstractService().byId(id);
		if( object != null ) {
			model.put("result", object);
			return new ResponseEntity<ModelMap>(model, HttpStatus.OK);
		}
		return new ResponseEntity<ModelMap>(HttpStatus.NOT_FOUND);
	}

}
