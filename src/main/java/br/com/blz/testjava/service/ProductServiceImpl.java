/**
 * 
 */
package br.com.blz.testjava.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.enums.ProductEnum;
import br.com.blz.testjava.exception.ProductException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;

/**
 * @author ocean
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	private List<ProductDTO> productsDto;

	@Override
	public ProductDTO getProductBy(Long sku) throws ProductException {

		if (sku == null) {
			throw new ProductException(ProductEnum.PRODUCT_NOT_EXISTS.getCd(),
					ProductEnum.PRODUCT_NOT_EXISTS.getMessage());
		}

		final Product product = this.productRepository.findOne(sku);

		if (product == null) {
			throw new ProductException(ProductEnum.PRODUCT_NOT_EXISTS.getCd(),
					ProductEnum.PRODUCT_NOT_EXISTS.getMessage());
		}

		return parseProductEntityToDTO(product);

	}

	@Override
	public List<ProductDTO> getProducts() {
		productsDto = null;

		final List<Product> products = this.productRepository.findAll();

		if (products != null && !products.isEmpty()) {

			productsDto = new ArrayList<ProductDTO>();

			products.forEach(p -> {

				final ProductDTO productDto = parseProductEntityToDTO(p);

				productsDto.add(productDto);

			});

		}
		return productsDto;
	}

	/**
	 * Parses the product entity to DTO.
	 *
	 * @param p the p
	 * @return the product DTO
	 */
	private ProductDTO parseProductEntityToDTO(Product p) {

		ProductDTO productDto = new ProductDTO();
		productDto.setName(p.getName());
		productDto.setSku(p.getSku());

		// Refresh Quantity Inventory
		p.getWarehouses().forEach(w -> {

			if (productDto.getInventory().getQuantity() == null) {
				productDto.getInventory().setQuantity(w.getQuantity());
			} else {
				productDto.getInventory().setQuantity(productDto.getInventory().getQuantity() + w.getQuantity());
			}
			productDto.getInventory().setWarehouses(p.getWarehouses());
		});

		// Validation Is Market Table
		if (productDto.getInventory().getQuantity() != null && productDto.getInventory().getQuantity() > 0) {
			productDto.setIsMarketable(Boolean.TRUE);
		} else {
			productDto.setIsMarketable(Boolean.FALSE);
		}

		return productDto;
	}

	@Override
	public Product create(ProductDTO productDTO) throws ProductException {

		if (productDTO.getSku() != null) {

			final Product prod = this.productRepository.findOne(productDTO.getSku());

			if (prod != null) {
				throw new ProductException(ProductEnum.PRODUCT_ALREADY_EXIST.getCd(),
						ProductEnum.PRODUCT_ALREADY_EXIST.getMessage());
			}
		}

		final Product product = new Product();
		product.setName(productDTO.getName());

		manageWarehouses(productDTO, product);

		return this.productRepository.save(product);
	}

	/**
	 * Manage warehouses.
	 *
	 * @param productDTO the product DTO
	 * @param product the product
	 */
	private void manageWarehouses(ProductDTO productDTO, final Product product) {
		if (productDTO.getInventory() != null && productDTO.getInventory().getWarehouses() != null) {

			productDTO.getInventory().getWarehouses().forEach(w -> {
				product.addWarehouses(w);
				w.setProduct(product);
			});

		}
	}

	@Override
	@Modifying
	public Product update(ProductDTO productDTO) throws ProductException {

		Product product = this.productRepository.findOne(productDTO.getSku());

		if (product == null) {
			throw new ProductException(ProductEnum.PRODUCT_NOT_EXISTS.getCd(),
					ProductEnum.PRODUCT_NOT_EXISTS.getMessage());
		}
		
		product.getWarehouses().clear();

		product = this.productRepository.saveAndFlush(product);		
		
		product.setName(productDTO.getName());	

		manageWarehouses(productDTO, product);
		
		product = this.productRepository.saveAndFlush(product);

		return product;
	}

	@Override
	public void delete(Long sku) throws ProductException {

		final Product product = this.productRepository.findOne(sku);

		if (product == null) {
			throw new ProductException(ProductEnum.PRODUCT_NOT_EXISTS.getCd(),
					ProductEnum.PRODUCT_NOT_EXISTS.getMessage());
		}

		this.productRepository.delete(sku);

	}

}
