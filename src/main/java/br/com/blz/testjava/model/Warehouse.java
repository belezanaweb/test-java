package br.com.blz.testjava.model;

import br.com.blz.testjava.enums.WarehouseType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Table(name = "warehouse")
@Entity
public class Warehouse implements Serializable {

	private static final long serialVersionUID = -4863995545961591229L;

    @JsonIgnore
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "locality")
	private String locality;

	@Column(name = "quantity")
	private Long quantity;

	@Column(name = "type")
    @Enumerated(EnumType.STRING)
	private WarehouseType type;
}
