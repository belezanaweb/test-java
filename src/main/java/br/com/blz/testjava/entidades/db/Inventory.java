package br.com.blz.testjava.entidades.db;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "inventory")
public class Inventory {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  
  
  
  @OneToMany(mappedBy = "inventory", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private List<Warehouse> warehouses;
  
  @ToString.Exclude 
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "produto_id")
  @JsonIgnore
  private Produto produto;
  
  // ---------------------
  
  @Transient
  private int quantity;
  
}
