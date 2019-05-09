package edu.aydin.sda.Entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Colors {
	
	@ManyToMany
	int id;
	String color;
	public Colors() {}
	public Colors(int id, String color) {
		this.id = id;
		this.color = color;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
