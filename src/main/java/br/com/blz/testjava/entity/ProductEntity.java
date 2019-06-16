/**
 * 
 */
package br.com.blz.testjava.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
@Entity
public class ProductEntity implements Serializable {

	private static final long serialVersionUID = 4729407338779880480L;
	
	@Id
	private Long sku;
	
	@Column
	private String name;
	
	

}
