package dist.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="delivery")
public class Delivery {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="process_id")
	private Process process;
	
	@Column(name="lat")
	private double lat;
	
	@Column(name="lon")
	private double lon;

	@Column(name="distance")
	private int distance;
	
	@Column(name="status")
	private String status;
	
	public Delivery() {}

	
	public Delivery(int id,Process process, double lat, double lon, String status) {
		super();
		this.id=id;
		this.process = process;
		this.lat = lat;
		this.lon = lon;
		this.status = status;
	}

	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	

	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Delivery [id=" + id + ", process=" + process + ", lat=" + lat + ", lon=" + lon + ", distance="
				+ distance + ", status=" + status + "]";
	}
	
}
