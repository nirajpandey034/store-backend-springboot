package storeBackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String title;
	private String price;
	private int availability;
	
	
	public Product(int id, String title, String price, int availability) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.availability = availability;
	}
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", price=" + price + ", availability=" + availability + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	
}
