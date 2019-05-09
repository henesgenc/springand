package edu.aydin.sda.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	Stocks Stock;
	public Products() {}
	public Products(String image, String name, String type, float price,String detail, Stocks stock){
		this.image = image;
		this.Product_Name = name;
		this.Product_Type = type;
		this.Price = price;
		this.Detail = detail;
		this.Stock = stock;
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
}
