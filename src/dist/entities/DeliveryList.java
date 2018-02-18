package dist.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="DelCarsList")
@Component
public class DeliveryList {

	List<Delivery> delCarsList;
	
	public List<Delivery> getDelCarsList(){
		return delCarsList;
	}
	
	public void setDeliveryList(List<Delivery> delCarsList) {
		this.delCarsList=delCarsList;
	}
}
