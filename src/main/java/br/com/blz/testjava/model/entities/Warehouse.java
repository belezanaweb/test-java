package br.com.blz.testjava.model.entities;

import br.com.blz.testjava.model.enums.TypeWarehouseEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames= {"idproduct", "idlocality"}, name="uk_warehouse"))
@DynamicUpdate
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @Enumerated(EnumType.STRING)
    private TypeWarehouseEnum type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idlocality", nullable = false, foreignKey= @ForeignKey(name = "fk_warehouse_locality"))
    private Locality locality;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idproduct", nullable = false, foreignKey = @ForeignKey(name = "fk_warehouse_product"))
    private Product product;

}
