package com.mphasis.foodbox.utility;

import java.io.Serializable;
import java.util.Objects;

/**
* FoodBox utility class to allow for http transfer without a full cuisine object
*
*/
public class FoodBoxUtility implements Serializable {

	private static final long serialVersionUID = 556213374437145740L;
	private Long id;
	private String name;
	private String description;
	private Double price;
	private Double discount;
	private Long cuisineId;
	private Boolean isEnabled;
	
	public FoodBoxUtility() {
	}

	public FoodBoxUtility(String name, String description, Double price, Double discount, Long cuisineId,
			Boolean isEnabled) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.cuisineId = cuisineId;
		this.isEnabled = isEnabled;
	}

	public FoodBoxUtility(Long id, String name, String description, Double price, Double discount, Long cuisineId,
			Boolean isEnabled) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.cuisineId = cuisineId;
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

	public Long getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(Long cuisineId) {
		this.cuisineId = cuisineId;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(cuisineId, description, discount, id, isEnabled, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodBoxUtility other = (FoodBoxUtility) obj;
		return Objects.equals(cuisineId, other.cuisineId) && Objects.equals(description, other.description)
				&& Objects.equals(discount, other.discount) && Objects.equals(id, other.id)
				&& Objects.equals(isEnabled, other.isEnabled) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price);
	}

	@Override
	public String toString() {
		return "FoodBoxUtility [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", discount=" + discount + ", cuisineId=" + cuisineId + ", isEnabled=" + isEnabled + "]";
	}
	
}
