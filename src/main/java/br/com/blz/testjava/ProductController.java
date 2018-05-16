package br.com.blz.testjava;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;

@RestController
public class ProductController {

	private static Map<Long, Product> database;

	public static Map<Long, Product> getDatabase() {
		if (database == null) {
			database = new HashMap<Long, Product>();
		}
		return database;
	}

	/**
	 * 
	 * Criação de produto
	 * 
	 * @param product
	 * @return
	 */
	@PostMapping("/product")
	public ResponseEntity<Object> save(@RequestBody Product product) {

		ResponseEntity<Object> lReturn = ResponseEntity.ok().build();
		try {

			/**
			 * Caso um produto já existente em memória tente ser criado com o mesmo sku uma exceção deverá ser lançada
			 */
			if (getDatabase().get(product.getSku()) != null) {
				lReturn = ResponseEntity.status(HttpStatus.FORBIDDEN).body("Produto já cadastrado");
			} else {
				getDatabase().put(product.getSku(), product);
			}

		} catch (Exception e) {
			lReturn = ResponseEntity.badRequest().build();
		}

		return lReturn;

	}

	/**
	 * 
	 * Edição de produto por sku
	 * 
	 * @param product
	 * @return
	 */
	@PostMapping("/product/edit")
	public ResponseEntity<Object> edit(@RequestBody Product product) {

		ResponseEntity<Object> lReturn = ResponseEntity.ok().build();
		try {

			getDatabase().put(product.getSku(), product);

		} catch (Exception e) {
			lReturn = ResponseEntity.badRequest().build();
		}

		return lReturn;

	}

	/**
	 * 
	 * Recuperação de produto por sku
	 * 
	 * @param sku
	 * @return
	 */
	@GetMapping("/product/{sku}")
	public ResponseEntity<Object> read(@PathVariable Long sku) {
		ResponseEntity<Object> lReturn = null;
		try {

			Product product = getDatabase().get(sku);

			/**
			 * Toda vez que um produto for recuperado por sku deverá ser calculado a propriedade: inventory.quantity
			 */
			product.getInventory().checkQuantity();
			
			/**
			 * Toda vez que um produto for recuperado por sku deverá ser calculado a propriedade: isMarketable
			 */
			product.checkMarketable();

			lReturn = ResponseEntity.ok(product);

		} catch (Exception e) {
			lReturn = ResponseEntity.badRequest().build();
		}

		return lReturn;

	}

	/**
	 * 
	 * Deleção de produto por sku
	 * 
	 * @param sku
	 * @return
	 */
	@DeleteMapping("/product/{sku}")
	public ResponseEntity<Object> remove(@PathVariable Long sku) {
		ResponseEntity<Object> lReturn = ResponseEntity.ok().build();
		try {

			getDatabase().remove(sku);

		} catch (Exception e) {
			lReturn = ResponseEntity.badRequest().build();
		}

		return lReturn;

	}

}
