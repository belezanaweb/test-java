package br.com.blz.testjava.api.werehouse.entity;

import br.com.blz.testjava.api.enums.TypeWarehouse;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "werehouse")
public class WarehouseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "locality")
  private String locality;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "typeWarehouse")
  private TypeWarehouse type;
}
