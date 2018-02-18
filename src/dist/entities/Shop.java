package dist.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="shop")
public class Shop {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="lon")
	private double lon;
	
	@Column(name="lat")
	private double lat;
	
	@Column(name="telephone")
	private int telephone;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@JoinColumn(name="shop_id")
	private List<Employee> employees;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="shop_id")
	private List<Mechanic> mechanic;
	
	
	public int getId() {
		return id;
	}

	public double getLon() {
		return lon;
	}

	public double getLat() {
		return lat;
	}

	public int getTelephone() {
		return telephone;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", lon=" + lon + ", lat=" + lat + ", telephone=" + telephone + ", ]";
	}
	
	
}
