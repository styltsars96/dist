package dist.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dist.entities.Car;
import dist.entities.Process;
import dist.service.MainService;

@Controller
@RequestMapping("/mechanic")
public class MechanicControler {
	
	@Autowired
	private MainService mainService;

	@GetMapping({"/",""})
	public String home(Model model, @CookieValue(value = "shopId", defaultValue = "1") int shopId) {
		model.addAttribute("processes",mainService.getProcesses(shopId,"Expected"));
		model.addAttribute("pageTitle","Mechanic");
		return "mechanic/home";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam("procId") int id, @RequestParam("carstatus") String status, Authentication authentication) {
		String auth = authentication.getAuthorities().iterator().next().getAuthority();//Get Role
		System.out.println("Status: "+status);
		if(auth.equals("mechanic")) {
			Process process = mainService.getProcessById(id);
			Car car = process.getCar();
			car.setStatus(status);//Triggers the discount operation for car.
			int disc = car.getDiscount();
			process.setStatus("Completed");
			process.setStatusDate(new Date());
			mainService.updateProcess(process);
			return "redirect:/mechanic/";
		}else return "redirect:/403";
	}
}
