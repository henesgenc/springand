package edu.aydin.sda.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Colors {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	private String color;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "colorProduct",
		joinColumns = @JoinColumn(name = "color_id"),
		inverseJoinColumns = @JoinColumn(name = "post_id")
	)
	private List<Products> products = new ArrayList<Products>();
	public Colors() {}
	public Colors(String color) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products.add(products);
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
