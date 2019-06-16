package br.com.blz.testjava.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Augusto Lemes
 * @since 16/06/2019
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseDTO {

	private String locality;
    private Integer quantity;
    private String type;
	
}
