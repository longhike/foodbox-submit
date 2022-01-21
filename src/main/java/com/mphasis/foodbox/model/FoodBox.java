package com.mphasis.foodbox.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
* FoodBox object mapped to food_box database table
*
*/
@Entity
public class FoodBox implements Serializable{

	private static final long serialVersionUID = 1764569854904456321L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true, nullable=false)
	private String name;
	@Column(nullable=false)
	private String description;
	@Column(nullable=false)
	private Double price;
	@Column(nullable=false)
	private Double discount;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="cuisine_id", referencedColumnName="id")
	private Cuisine cuisine;
	@Column(nullable=false)
	private Boolean isEnabled;
	
	public FoodBox() {}
	
	public FoodBox(String name, String description, Double price, Double discount, Cuisine cuisine, Boolean isEnabled) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.cuisine = cuisine;
		this.isEnabled = isEnabled;
	}
	public FoodBox(Long id,String name, String description, Double price, Double discount, Cuisine cuisine, Boolean isEnabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.cuisine = cuisine;
		this.isEnabled = isEnabled;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Cuisine getCuisine() {
		return cuisine;
	}
	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
	
}

