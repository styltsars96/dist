package dist.controllers;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dist.entities.Delivery;
import dist.entities.Process;
import dist.service.MainService;


@Controller
@RequestMapping("/employee")
public class EmployeeControler {

	@Autowired
	private MainService mainService;
	
	@GetMapping({"/",""})
	public String home(Model model) {
		model.addAttribute("pageTitle", "Employee");
		return "employee/home";
	}
	
	@GetMapping("/newProcess")//USEFULL
	public String newprocess(Model model, @CookieValue(value = "shopId") int shopId ) {
		Process process = new Process();
		model.addAttribute("newProcess", process);
		model.addAttribute("pageTitle", "New Process");
		return "employee/newprocess";
	}
	
	@PostMapping("/saveProcess")//USEFULL
	public String saveProcess(@CookieValue(value = "shopId", defaultValue = "0") int shopId,@ModelAttribute("newProcess") Process process,Model model,
			HttpServletRequest request) {
		
		try {
			//BIG DEBUG BLOCK///////////////////////////////////////
			Map<String, String> map = new HashMap<String, String>();

	        Enumeration headerNames = request.getHeaderNames();
	        while (headerNames.hasMoreElements()) {
	            String key = (String) headerNames.nextElement();
	            String value = request.getHeader(key);
	            map.put(key, value);
	        }
			System.out.println("POST Request has: "+ map);//DEBUG
			System.out.println("Saving: "+ process);//DEBUG
			///////////////////////////////////////////////////////
			process.setStatus("Expected");
			process.setShop(mainService.getShopById(shopId));
			process.setStatusDate(new Date());
			process.getCar().setStatus(null);
			mainService.saveProcess(process);
			
			model.addAttribute("process",process);
			model.addAttribute("pageTitle","Success");
			return "employee/processSaved";
		}catch(Exception e) {
			return "employee/saveFailed";
		}
	}
	 
	@GetMapping("/completedProcs")
	public String complProc(@CookieValue(value = "shopId", defaultValue = "1")int shopId, Model model) {
		model.addAttribute("processes",mainService.getProcesses(shopId,"Completed"));
		model.addAttribute("pageTitle", "ComplProcs");
		return "employee/complProcs";
	}
	
	@GetMapping("/deliveredCars")//employee sees the arrived cars with purpose to valid them.
	public String delcars(@CookieValue(value = "shopId", defaultValue = "1")int shopId,Model model) {
		model.addAttribute("delcars",mainService.getDeliverCarsByStatus("Received",shopId));
		model.addAttribute("pageTitle", "Delivered Cars");
		return "employee/carsForValidation";
	}
	
	@GetMapping("/deliverCar")
	public String getDelcar(@RequestParam("plate") String plate,Model model) {
		try {
			Delivery delivery = mainService.getDeliveryCarByPlate(plate);
			model.addAttribute("deliveredCar",delivery);
			System.out.println(delivery);
			model.addAttribute("pageTitle", "Delivered Car");
			return "employee/carValidation";
		}catch(Exception e) {
			return "anyFail";
		}
	}
	
	@PostMapping("/validateCar")
	public String saveDelcar(@RequestParam("plate")String plate,@RequestParam("model")String car_model,@RequestParam("fuelType")String fuelType,
			@RequestParam("firstRelease")int firstRelease) {
		System.out.println("Approving!!!");
		//Fetch data.
		Delivery delivery = mainService.getDeliveryCarByPlate(plate);
		delivery.getProcess().getCar().setModel(car_model);
		delivery.getProcess().getCar().setFuelType(fuelType);
		delivery.getProcess().getCar().setFirstRelease(firstRelease);
		System.out.println(delivery);
		//Inform data & save.
		delivery.setStatus("Approved");
		delivery.getProcess().setStatus("Expected");
		delivery.getProcess().setStatusDate(new Date());
		mainService.updateDelivery(delivery);
		//Redirect.
		return "redirect: deliveredCars";
	}
	
	@GetMapping("/forTransit")
	public String carsForTransit(@CookieValue(value = "shopId", defaultValue = "1") int shopId, Model model){
		model.addAttribute("carsForTransit", mainService.getDeliverCarsByStatus("Declared",shopId));
		System.err.println(mainService.getDeliverCarsByStatus("Declared",shopId));
		return "employee/carsForTransit";
	}
	
	@PostMapping("/forTransit")
	public String carArrive(@RequestParam("plate")String plate, Model model){
		Delivery delivery = mainService.getDeliveryCarByPlate(plate);
		delivery.setStatus("Received");
		delivery.getProcess().setStatusDate(new Date());
		mainService.updateDelivery(delivery);
		//Redirect
		return "redirect: deliveredCars";
	}
	
}
