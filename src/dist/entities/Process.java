package dist.entities;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name ="process")
public class Process {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="car_id")
	private Car car;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="shop_id")
	private Shop shop;
	
	@Column(name = "status")
	private String status;
	      
	@Column(name="Status_Date")
	@Temporal(TemporalType.DATE) 
	private Date statusDate;

	
	public Process() {}
	
	public Process( Car car, String status, Shop shop) {
		super();
		this.car = car;
		this.shop = shop;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}
	
	public String getStatusDateStr() {
		return DateUtils.formatDate(statusDate);
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	
	public void setStatusDate(String statusDate) {
		try {
			this.statusDate = DateUtils.parseDate(statusDate);
		}catch(ParseException e) {
			System.out.println("Process: Couldn't Update Date");
			e.printStackTrace();
		}
	}
	
	public void setStatusDate(String statusDate, String format) {
		try {
			this.statusDate = DateUtils.parseDate(statusDate, format);
		}catch(ParseException e) {
			System.out.println("Process: Couldn't Update Date");
			e.printStackTrace();
		}
	}
	

	public void setStatusDate(int year, int month, int day) {
		try {
			this.statusDate = DateUtils.parseDate(""+day+"/"+month+"/"+year+"");
		}catch(ParseException e) {
			System.out.println("Process: Couldn't Update Date");
			e.printStackTrace();
		}
	}


	@Override
	public String toString() {
		return "Process [id=" + id + ", car=" + car + ", shop=" + shop + ", status=" + status + ", statusDate="
				+ DateUtils.formatDate(statusDate) + "]";
	}	
	
}