package br.com.blz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.blz.entity.Deposito;

@RepositoryRestResource
public interface DepositoRepository extends CrudRepository<Deposito, Integer>{

}
