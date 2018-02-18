package dist.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import dist.entities.ProcessList;
import dist.service.MainService;
import dist.entities.Car;
import dist.entities.Customer;
import dist.entities.Delivery;
import dist.entities.Process;

@RestController
@RequestMapping("/api/process")
public class ProcessApi {
	@Autowired
    private MainService mainService;
	
	@Autowired 
	private ProcessList processList;
	
	@GetMapping(value = "/list/{customerAfm}",  produces =  "application/json")
	public ProcessList getProcessByCustomer(@PathVariable("customerAfm") String customerAfm) {
		
		List<Process> processes = mainService.getProcessesByCustomer(customerAfm);
        System.out.println("processes :" + processes);
        this.processList.setProcessList(processes);
		return processList;
	}
	
	@RequestMapping(value = "/{plate}", method = RequestMethod.GET, produces = "application/json")
    public Process getProcess(@PathVariable("plate") String plate) {
            Process process = mainService.getProcessByPlate(plate);
            System.out.println("APP SERVER: Process :" + process);
            return process;
    }
	
	@PostMapping(value="/new",produces="application/json")
	public String newProcess (@RequestBody Delivery delivery) {
		System.out.println("App Server try to save delivery:"+delivery);
		
		try {
			Customer customer = mainService.getCustomerByAfm(delivery.getProcess().getCar().getCustomer().getAfm());
			if(customer==null) return "{\"status\":400,\"error\":\"There is not that customer.\"}";
			delivery.getProcess().getCar().setCustomer(customer);
			
			Car car=mainService.getCarByPlate(delivery.getProcess().getCar().getLicencePlate());
			
			if(car!=null) return"{\"status\":400,\"error\":\"The car already has been writen.\"}";
			
			delivery.getProcess().setStatusDate(new Date());
			delivery.getProcess().setStatus("Awaiting Approval");
			delivery.setStatus("Declared");
			mainService.saveDelivery(delivery);

			return "{\"status\":200}";
		}catch(Exception e) {
			e.printStackTrace();
			return "{\"status\":400}";
		}
	}
	
}
