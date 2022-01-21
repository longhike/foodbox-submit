package com.mphasis.foodbox.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
* Cuisine object mapped to cuisine database table
*
*/
@Entity
public class Cuisine implements Serializable {

	private static final long serialVersionUID = 8342176571739288790L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true, nullable=false)
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private List<FoodBox> foodBoxes;
	
	public Cuisine() {}
	
	public Cuisine(String name) {
		this.name=name;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(foodBoxes, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuisine other = (Cuisine) obj;
		return Objects.equals(foodBoxes, other.foodBoxes) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}
	
}