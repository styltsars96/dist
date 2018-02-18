package dist.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement(name = "Car")
@Entity
@Table(name="car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	@ManyToOne(fetch=FetchType.EAGER,cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "licence_plate")
	private String licencePlate;
	
	@Column(name = "fuel_type")
	private String fuelType;
	
	@Column(name = "first_release")
	private int firstRelease;
	
	@Column(name = "status")
	private String status;

	@Column(name = "discount")
	private Integer discount;
	
	public Car() {}
	
	public Car(Customer customer, String model, String licencePlate, String fuelType, int firstRelease, String status) {
		super();
		this.customer = customer;
		this.model = model;
		this.licencePlate = licencePlate;
		this.fuelType = fuelType;
		if (firstRelease < 1900 || firstRelease > 10000) System.out.println("CAR: Problem with firstRelease...");
		this.firstRelease = firstRelease;
		this.status = status;
		this.discount = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public int getFirstRelease() {
		return firstRelease;
	}

	public void setFirstRelease(int firstRelease) {
		if (firstRelease < 1900 || firstRelease > 10000) System.out.println("CAR: Problem with firstRelease...");
		this.firstRelease = firstRelease;
	}
	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
		if(this.status == null) return;
		if(this.discount == null) this.discount = 0;
		//THIS METHOD TRIGGERS THE DISCOUNT OPERATION!!!
		int age = DateUtils.getAge(this.firstRelease);
		int value = -1;//for error,
		//Max Value
		if (age>=0 && age<5) {
			value = 800;
		}else if ((age>=5 && age<10) || age>20) {
			value = 1000;
		} else if (age>=10 && age<=20) {
			value = 1200;
		}
		switch (this.status) {
		case "Good":
			//Value is the same.
			break;
		case "Moderate" :
			value = (value*4)/5;
			break;
		case "Bad" :
			value = value/2;
			break;
		default:
			System.out.println("Discount Operation: Something's Wrong");
			return;
		}
		this.discount += value;
		//---------------------------------------------
	}
	
	public Integer getDiscount() {
		return this.discount;
	}
	
	public String getDiscountStr() {
		if(this.discount == null) return "Αναμένεται έλεγχος από τον μηχανικό";
		return "Επιβράβευση για  "+this.licencePlate+" είναι " + this.discount + " Ευρώ";
	}
	
	public void setDiscount(int disc) {
		if(this.discount == null) this.discount = 0;
		this.discount += disc;
	}
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", customer=" + customer + ", model=" + model + ", licencePlate=" + licencePlate
				+ ", fuelType=" + fuelType + ", firstRelease=" + firstRelease + ", status=" + status + "]";
	}
	
	
}
