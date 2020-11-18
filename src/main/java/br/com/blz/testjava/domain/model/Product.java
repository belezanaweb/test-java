package br.com.blz.testjava.domain.model;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

  private Long sku;
  private String name;
  private Inventory inventory;

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

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public Boolean isMarketable() {
    return (inventory == null ? false : inventory.getQuantity() > 0);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(sku, product.sku) &&
      Objects.equals(name, product.name) &&
      Objects.equals(inventory, product.inventory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sku, name, inventory);
  }
}
