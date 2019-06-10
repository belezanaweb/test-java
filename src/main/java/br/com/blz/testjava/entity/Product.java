package br.com.blz.testjava.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbProduct")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long sku;
	
	private String name;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Inventory.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "Inventory_id")
	private Inventory inventory;
	
	@Value(value="true")
	private Boolean isMarketable;
}