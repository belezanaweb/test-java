package br.com.blz.testjava.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.blz.testjava.data.Product;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

	@Override
	public EntityModel<Product> toModel(Product product) {

		return EntityModel.of(product,
		        linkTo(methodOn(ProductController.class).one(product.getSku())).withSelfRel(),
		        linkTo(methodOn(ProductController.class).all()).withRel("products"));

	}

}
