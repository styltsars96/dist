package dist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="internal_user")
public class InternalUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="password")
	private String password;
	
	@Column(name="shop_id")
	private Integer shopId;
	
	@Column(name="shop_role")
	private String shopRole;
	
	@Column(name="enabled")
	private short enabled;
	
	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(short enabled) {
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getShopId() {
		if(shopId==null) return 0;
		return this.shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopRole() {
		return shopRole;
	}

	public void setShopRole(String shopRole) {
		this.shopRole = shopRole;
	}
	
	@Override
	public String toString() {
		return fullname;
	}
	
}