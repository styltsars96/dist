package dist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Customer")
@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "fullname")
    private String fullname;
    
	@Column (name="passcode")
	private String pass;
	
	@Column(name="afm")
	private long afm;
	
	@Column(name="telephone")
	private long telephone;
	
	public Customer() {
            
    }
    
    public Customer(String fullname, String pass, int afm, int telephone) {
		super();
		this.fullname = fullname;
		this.pass = pass;
		this.afm = afm;
		this.telephone = telephone;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public long getAfm() {
		return afm;
	}

	public void setAfm(long afm) {
		this.afm = afm;
	}
	
	public long getTelephone() {
		return telephone;
	}

	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", fullname=" + fullname + ", afm=" + afm + ", telephone=" + telephone + "]";
	}

}
