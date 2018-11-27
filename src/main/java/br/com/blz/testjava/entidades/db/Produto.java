package br.com.blz.testjava.entidades.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

@Data
@Entity
@Table(name = "produto")
public class Produto {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sku")
  @JsonAlias({ "sku", "id" })
  private int sku;
  
  @Column(name = "name")
  private String name;
  
  @OneToOne(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Inventory inventory;
  
  // -----------------------------------------
  
  @Transient
  @JsonAlias({ "marketable", "isMarketable" })
  @JsonProperty("isMarketable")
  private boolean marketable;
  
}
