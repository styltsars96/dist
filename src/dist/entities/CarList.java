package dist.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="CarList")
@Component
public class CarList {
	
	List<Car> carList;

	public List<Car> getCarList(){
		return carList;
	}
	
	public void setCarList(List<Car> carList) {
		this.carList=carList;
	}
}
