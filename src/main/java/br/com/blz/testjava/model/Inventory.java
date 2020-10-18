package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {

	private static final long serialVersionUID = 4369025964227817468L;

    @JsonIgnore
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;

	@Transient
	private Long quantity;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,  orphanRemoval = true)
    @JoinColumn(name = "warehouse_id")
	private List<Warehouse> warehouses = new ArrayList<>();

	public Long getQuantity() {
        return CollectionUtils.isEmpty(this.warehouses) ? 0  : this.warehouses.stream().mapToLong(Warehouse::getQuantity).sum();
	}
}
