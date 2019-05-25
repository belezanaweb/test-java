package br.com.blz.testjava;

import static org.junit.Assert.assertThat;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.models.ProductEntity;
import br.com.blz.repository.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestJavaApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	ProductRepository productDAO;

	@Test
	public void should_find_no_products_if_repository_is_empty() {
		Iterable<ProductEntity> products = productDAO.findAll();
		for (ProductEntity productEntity : products) {
			System.out.println(productEntity.toString());
		}
		assertThat(products).isEmpty();
	}

}
