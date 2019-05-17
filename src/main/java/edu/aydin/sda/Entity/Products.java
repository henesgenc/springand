package edu.aydin.sda.Entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Table(name="products")
@Entity
public class Products {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String image;
	private String Product_Name;
	private String Product_Type;
	private float Price;
	private String Detail;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	Stocks Stock = new Stocks();
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "colorProduct",
		joinColumns = @JoinColumn(name = "post_id"),
		inverseJoinColumns = @JoinColumn(name = "color_id")
	)
	private List<Colors> colors = new ArrayList<Colors>();
	public Products() {}
	public Products(String image, String product_Name, String product_Type, float price, String detail) {
		this.image = image;
		this.Product_Name = product_Name;
		this.Product_Type = product_Type;
		this.Price = price;
		this.Detail = detail;
	}

	public int getID() {
		return id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getProduct_Name() {
		return Product_Name;
	}
	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}
	public String getProduct_Type() {
		return Product_Type;
	}
	public void setProduct_Type(String product_Type) {
		Product_Type = product_Type;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public String getDetail() {
		return Detail;
	}
	public void setDetail(String detail) {
		Detail = detail;
	}
	public Stocks getStock() {
		return Stock;
	}
	public void setStock(Stocks stock) {
		this.Stock = stock;
	}
	public List<Colors> getColors() {
		return colors;
	}
	public void setColors(Colors colors) {
		this.colors.add(colors);
	}
}
