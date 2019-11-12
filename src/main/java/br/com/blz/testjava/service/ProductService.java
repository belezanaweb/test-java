package br.com.blz.testjava.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.exception.ApiErrorResponse;
import br.com.blz.testjava.exception.ApiException;
import br.com.blz.testjava.model.DatabaseSequence;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.repository.ProductRepository;

@Service
@CacheConfig(cacheNames = { "productCache" })
public class ProductService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private CacheManager cacheManager;

	public ProductDto saveProduct(ProductDto productDto, Boolean isNew) throws ApiException {
		String operationType = isNew ? "salvar" : "atualizar";
		try {
			Product product = convertToEntity(productDto);
			validateSku(product, isNew);
			return calculateQuantity(productRepository.save(product));
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw ApiException.of(ApiErrorResponse.of(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					"Erro ao " + operationType + " produto!",
					"Ocorreu um erro ao " + operationType + " o produto, Por gentileza, tente novamente."));
		}
	}

	private void validateSku(Product product, Boolean isNew) throws ApiException {
		if (isNew) {
			if (product.getSku() == null) {
				throw ApiException.of(ApiErrorResponse.of(String.valueOf(HttpStatus.BAD_REQUEST.value()),
						"Ocorreu um erro ao salvar o produto!",
						"Não foi possivel salvar o produto, Por gentileza, informe o codigo SKU."));
			} else if (productRepository.findBySku(product.getSku()) != null) {
				throw ApiException.of(ApiErrorResponse.of(String.valueOf(HttpStatus.BAD_REQUEST.value()),
						"Ocorreu um erro ao salvar o produto!",
						"O produto com o código SKU " + product.getSku() + " já encontra-se cadastrado."));
			} else {
				product.setId(generateSequence(Product.SEQUENCE_NAME));
			}
		} else {
			if (product.getSku() == null) {
				throw ApiException.of(ApiErrorResponse.of(String.valueOf(HttpStatus.BAD_REQUEST.value()),
						"Ocorreu um erro ao atualizar o produto!",
						"Não foi possivel atualizar o produto, Por gentileza, informe o codigo SKU."));
			} else {
				product.setId(productRepository.findBySku(product.getSku()).getId());
				cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
			}
		}
	}

	@Cacheable(cacheNames = { "productCache" })
	public ProductDto getProductBySku(Long sku) throws ApiException {
		logger.info("Buscando produto - sku: {}", sku);
		try {
			Product product = productRepository.findBySku(sku);
			if (product == null) {
				throw ApiException.of(
						ApiErrorResponse.of(String.valueOf(HttpStatus.NO_CONTENT.value()), "Produto não encontrado!",
								"Não foi possivel localizar o produto, Por gentileza, verifique o código informado."));
			}
			return calculateQuantity(product);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw ApiException.of(ApiErrorResponse.of(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					"Erro ao buscar produto!", "Ocorreu um erro ao buscar o produto, Por gentileza, tente novamente."));
		}
	}

	public String deleteProductBySku(Long sku) throws ApiException {
		try {
			Product product = productRepository.findBySku(sku);
			if (product == null) {
				throw ApiException.of(ApiErrorResponse.of(String.valueOf(HttpStatus.NO_CONTENT.value()),
						"Produto não excluido!",
						"Não foi possivel localizar o produto para exclusao, Por gentileza, verifique o código informado."));
			}
			productRepository.deleteById(product.getId());
			return "Produto excluido com sucesso!";
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw ApiException.of(ApiErrorResponse.of(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					"Erro ao excluir produto!",
					"Ocorreu um erro ao excluir o produto, Por gentileza, tente novamente."));
		}
	}

	private ProductDto calculateQuantity(Product product) {
		if (product.getInventory() == null || product.getInventory().getWarehouses() == null
				|| product.getInventory().getWarehouses().isEmpty()) {
			product.setIsMarketable(false);
		} else {
			Integer quantity = Integer.valueOf(0);
			for (Warehouse warehouse : product.getInventory().getWarehouses()) {
				quantity += warehouse.getQuantity();
			}
			product.getInventory().setQuantity(quantity);
			product.setIsMarketable(quantity > 0);
		}
		return convertToDto(product);
	}

	private Product convertToEntity(ProductDto productDto) {
		return modelMapper.map(productDto, Product.class);
	}

	private ProductDto convertToDto(Product product) {
		return modelMapper.map(product, ProductDto.class);
	}

	private long generateSequence(String seqName) {
		DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
				new Update().inc("sequence", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSequence() : 1;
	}

}
