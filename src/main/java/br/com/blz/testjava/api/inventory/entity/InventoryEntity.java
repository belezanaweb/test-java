package br.com.blz.testjava.api.inventory.entity;

import br.com.blz.testjava.api.werehouse.entity.WarehouseEntity;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "inventory")
public class InventoryEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<WarehouseEntity> werehouses;
}
