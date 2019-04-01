package br.com.blz.testjava.business.interfaces;

import br.com.blz.testjava.model.dto.ProductDTO;
import br.com.blz.testjava.model.vo.ProductVO;

public interface IProductService {
	
	ProductVO getProductBySku(Long sku);
	ProductVO createProduct(ProductDTO product);
	ProductVO updateProduct(ProductDTO product);
	void deleteProductBySku(Long sku);

}
