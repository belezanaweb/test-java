package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_product")
public class Product implements Serializable{

	private static final long serialVersionUID = -6825884295044627239L;

    @JsonIgnore
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	@Column(name = "sku", unique = true)
	private Long sku;

	@Column(name = "name")
	private String name;

	@Transient
    @JsonProperty("isMarketable")
	private Boolean marketable;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "inventory_id")
	private Inventory inventory;

	public Boolean getMarketable() {
	    return !ObjectUtils.isEmpty(this.inventory) &&  !ObjectUtils.isEmpty(this.inventory.getQuantity()) && this.getInventory().getQuantity() > 0;
	}
}
