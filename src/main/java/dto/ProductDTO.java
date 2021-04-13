package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {

	private Long sku;
	private String name;

	@JsonProperty("inventory")
	private InventoryDTO inventoryDto;

	public ProductDTO() {

	}

	public ProductDTO(Long sku, String name) {
		super();
		this.sku = sku;
		this.name = name;
	}



	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InventoryDTO getInventoryDto() {
		return inventoryDto;
	}

	public void setInventoryDto(InventoryDTO inventoryDto) {
		this.inventoryDto = inventoryDto;
	}

	 public boolean isMarktable() {
	        Integer sum = this.inventoryDto.getWarehousesDTO().stream()
	                .map(p -> p.getQuantity())
	                .reduce(0, Integer::sum);
	        if (sum > 0) return true;
	        return isMarktable();
	    }

	
}
