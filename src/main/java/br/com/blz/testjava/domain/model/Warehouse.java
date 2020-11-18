package br.com.blz.testjava.domain.model;

import java.io.Serializable;
import java.util.Objects;

public class Warehouse implements Serializable {

  private String locality;
  private int quantity;
  private String type;

  public Warehouse() {
  }

  public Warehouse(String locality, int quantity, String type) {
    this.locality = locality;
    this.quantity = quantity;
    this.type = type;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Warehouse warehouse = (Warehouse) o;
    return quantity == warehouse.quantity &&
      Objects.equals(locality, warehouse.locality) &&
      Objects.equals(type, warehouse.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(locality, quantity, type);
  }
}
