package br.com.blz.testjava.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Inventory implements Serializable {

  private List<Warehouse> warehouses;

  public List<Warehouse> getWarehouses() {
    return warehouses;
  }

  public void setWarehouses(List<Warehouse> warehouses) {
    this.warehouses = warehouses;
  }

  public Integer getQuantity() {
    return Optional.ofNullable(warehouses)
      .orElse(new ArrayList<>())
      .stream().mapToInt(Warehouse::getQuantity).sum();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Inventory inventory = (Inventory) o;
    return Objects.equals(warehouses, inventory.warehouses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(warehouses);
  }
}
