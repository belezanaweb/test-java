package br.com.blz.testjava.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.model.Product;

@Component
public class ProductsMock {

	public static List<Product> products = new ArrayList<>();

}
