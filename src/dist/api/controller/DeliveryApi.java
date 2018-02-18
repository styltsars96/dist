package dist.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dist.entities.*;
import dist.service.MainService;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryApi {

	@Autowired
    private MainService mainService;
	
	@PostMapping(value="/new",produces="application/json")
	private Delivery newDelivery(@RequestBody Delivery delivery) {
		System.out.println("App Server try to save delivery:"+delivery);//TODO: Delete this.
		//Check afm
		long afm = delivery.getProcess().getCar().getCustomer().getAfm();
		System.err.println(afm);
		if (afm == 0 ) {System.err.println("Afm is zero.");return null;}
		
		try {
			//Find the customer by afm.
			Customer customer = mainService.getCustomerByAfm(afm);
			System.err.println(customer);
			if(customer==null) return null;
			
			//Check if there is already the same licence plate.
			Car car=mainService.getCarByPlate(delivery.getProcess().getCar().getLicencePlate());
			if(car != null) return null;
			
			//Inform & save data.
			delivery.getProcess().getCar().setCustomer(customer);
			delivery.getProcess().setStatusDate(new Date());
			delivery.getProcess().setStatus("Awaiting Approval");
			delivery.setStatus("Declared");
			
			car = delivery.getProcess().getCar();
			//Αυτό είναι για το delivery api, αφου πρωτα μπουν τα locations.
			int disc = 0;
			int distance = delivery.getDistance();
			if(distance < 10) {
				disc = -20;
			}else if(distance < 30) {
				disc = -30;
			}else if(distance < 100) {
				disc = -40;
			}
			//Inform data & save.
			car.setDiscount(disc);
			mainService.saveDelivery(delivery);
			return delivery;
		}catch(Exception e) {
			System.out.println("Application Server caught: "+e.getMessage());
			return null;
		}
	
	}
	
}
