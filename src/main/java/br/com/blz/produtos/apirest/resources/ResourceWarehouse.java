package br.com.blz.produtos.apirest.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.com.blz.produtos.apirest.entity.EntityWarehouse;
import br.com.blz.produtos.apirest.repository.RepositoryWarehouse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Warehouse")
@CrossOrigin(origins="*")
public class ResourceWarehouse {
	
	@Autowired
	RepositoryWarehouse warehouseRepository;
	
	@GetMapping("/warehouses")
	@ApiOperation(value="Retorna uma lista de warehouse")
	@ResponseStatus(HttpStatus.OK)
	public List<EntityWarehouse> listarWarehouses(){
		return warehouseRepository.findAll();
	}
	
	@PostMapping("/warehouse")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Cadastra um warehouse")
	public EntityWarehouse inserirWarehouse(@RequestBody EntityWarehouse warehouse){
		return warehouseRepository.save(warehouse);
	}
}
