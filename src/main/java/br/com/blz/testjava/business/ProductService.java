package br.com.blz.testjava.business;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.business.interfaces.IProductService;
import br.com.blz.testjava.config.MessagesUtils;
import br.com.blz.testjava.exceptions.NotFoundException;
import br.com.blz.testjava.exceptions.ProductDuplicatedException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.dto.ProductDTO;
import br.com.blz.testjava.model.vo.InventoryVO;
import br.com.blz.testjava.model.vo.ProductVO;
import br.com.blz.testjava.model.vo.WarehouseVO;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.util.Utils;

@Service
public class ProductService implements IProductService{

	private static final String SKUL_NOT_FOUND = "sku.not.found";

	@Autowired
	private ProductRepository repository;

	@Autowired
	private WarehouseService warehouseService;

	@Override
	public ProductVO getProductBySku(Long sku){
		ProductVO productVO = null;

		List<WarehouseVO> listWarehousesVO = new ArrayList<>();

		Product product = Optional.ofNullable(repository.findOne(Utils.skuVerify(sku)))
				.orElseThrow(() -> new NotFoundException(MessageFormat.format(MessagesUtils.getMessage(SKUL_NOT_FOUND),sku)));

		List<Warehouse> listWarehouses = Optional.ofNullable(warehouseService.getWarehouseBySku(sku))
				.orElseThrow(() -> new NotFoundException(MessageFormat.format(MessagesUtils.getMessage("warehouse.not.found"),sku)));

		Integer quantity = listValidator(listWarehouses)
				.map(Warehouse::getQuantity)
				.reduce(NumberUtils.INTEGER_ZERO, Integer::sum);

		listValidator(listWarehouses)
		.forEach(w-> listWarehousesVO.add(new WarehouseVO(w.getLocality(), w.getQuantity(), w.getType())));

		Boolean isMarktable = quantity > 0 ? Boolean.TRUE : Boolean.FALSE;

		productVO = new ProductVO(product.getProductSku(), product.getName(), 
				new InventoryVO(quantity, listWarehousesVO), isMarktable);

		return productVO;

	}

	@Override
	public ProductVO createProduct(ProductDTO productDto){
		ProductVO product = null;

		if(Objects.nonNull(repository.findOne(Utils.skuVerify(productDto.getSku())))){
			throw new ProductDuplicatedException(MessageFormat.format(MessagesUtils.getMessage("sku.duplicated"),productDto.getSku()));
		}

		if(Objects.nonNull(productDto.getInventory())) {
			Optional.ofNullable(productDto.getInventory().getWarehouses())
			.orElseGet(Collections::emptyList)
			.stream()
			.filter(Objects::nonNull)
			.forEach(w -> warehouseService.createWarehouse(new Warehouse(w.getLocality(), w.getQuantity(), w.getType(), productDto.getSku())));
		}

		repository.save(new Product(productDto.getSku(), Utils.verifyName(productDto.getName())));

		product = getProductBySku(productDto.getSku());

		return product;
	}

	@Override
	public ProductVO updateProduct(ProductDTO productDto){
		ProductVO productVO = null;

		Product product = Optional.ofNullable(repository.findOne(Utils.skuVerify(productDto.getSku())))
				.orElseThrow(() -> new NotFoundException(MessageFormat.format(MessagesUtils.getMessage(SKUL_NOT_FOUND),productDto.getSku())));

		if(Objects.nonNull(productDto.getInventory())) {
			Optional.ofNullable(productDto.getInventory().getWarehouses())
			.orElseGet(Collections::emptyList)
			.stream()
			.filter(Objects::nonNull)
			.forEach(w -> warehouseService.updateWarehouse(new Warehouse(w.getLocality(), w.getQuantity(), w.getType(), productDto.getSku())));
		}
		
		repository.save(new Product(product.getProductSku(), Utils.verifyName(product.getName())));

		productVO = getProductBySku(productDto.getSku());

		return productVO;
	}

	@Override
	public void deleteProductBySku(Long sku){

		repository.delete(Optional.ofNullable(repository.findOne(Utils.skuVerify(sku)))
				.orElseThrow(() -> new NotFoundException(MessageFormat.format(MessagesUtils.getMessage(SKUL_NOT_FOUND),sku))));

		Optional.ofNullable(warehouseService.getWarehouseBySku(sku))
		.orElseGet(Collections::emptyList)
		.stream()
		.filter(Objects::nonNull)
		.forEach(w -> warehouseService.deleteWarehouseBySku(w));

	}

	/**
	 * @param listWarehouses
	 * @return Stream<Warehouse>
	 */
	private Stream<Warehouse> listValidator(List<Warehouse> listWarehouses) {
		return Optional.ofNullable(listWarehouses)
				.orElseGet(Collections::emptyList)
				.stream()
				.filter(Objects::nonNull);
	}

}
