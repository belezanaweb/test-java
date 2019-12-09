package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.SKUDTO;
import br.com.blz.testjava.dto.WarehousesDTO;
import br.com.blz.testjava.repository.SKURepository;

@Service
public class SKUService {

	@Autowired
	SKURepository SKUrepo;

	/**
	 * Toda vez que um produto for recuperado por sku deverá ser calculado a propriedade: inventory.quantity
	 * @param sku
	 * @return
	 */
	public SKUDTO getSKU(int sku) {
		SKUDTO skuDTO = SKUrepo.getSKU(sku);
		if (skuDTO != null) {
			calculateInventoryQuantity(skuDTO);

			verifyMarketable(skuDTO);
		}
		return skuDTO;
	}

	/**
	 * 
	 * @param sku
	 * @return
	 */
	public SKUDTO removeSKU(int sku) {
		return SKUrepo.deleteSKU(sku);
	}

	/**
	 * 
	 * @param SKU
	 * @return
	 * @throws Exception
	 */
	public SKUDTO createSKU(SKUDTO skuDTO) throws Exception {
		return SKUrepo.createSKU(skuDTO);
	}

	/**
	 * @
	 * @param skuDTO
	 */
	private void verifyMarketable(SKUDTO skuDTO) {
		
		skuDTO.setMarketable(skuDTO.getInventory().getQuantity() > 0 ? true : false);

	}

	/**
	 * 
	 * @param skuDTO
	 */
	private void calculateInventoryQuantity(SKUDTO skuDTO) {
		if (skuDTO.getInventory() != null && skuDTO.getInventory().getWarehousesDTO() != null) {

			skuDTO.getInventory().setQuantity(skuDTO.getInventory().getWarehousesDTO().stream()
					.map((WarehousesDTO w) -> w.getQuantity() > 0 ? w.getQuantity() : 0).reduce(0, Integer::sum));

		} else {
			if (skuDTO.getInventory() == null) {
				skuDTO.setInventory(new InventoryDTO());
			}
			skuDTO.getInventory().setQuantity(0);
		}
	}

	/**
	 * 
	 * @param skuDTO
	 * @return
	 */
	public SKUDTO updateSKU(SKUDTO skuDTO) {
		return SKUrepo.updateSKU(skuDTO);
	}

}
