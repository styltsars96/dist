package dist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "mechanic")
public class Mechanic {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="shop_id")
	private int shop_id;

	@Transient
	private Shop shop;
	
	public Mechanic() {}
	
	public Mechanic( String fullname, String password, Shop shop) {
		super();
		this.fullname = fullname;
		this.shop = shop;
	}

	public int getId() {
		return id;
	}

	public String getFullname() {
		return fullname;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public int getShop_id() {
		return shop_id;
	}
	
	@Override
	public String toString() {
		return "Mechanic [id=" + id + ", fullname=" + fullname + ", shop=" + shop + "]";
	}
	
	
}
