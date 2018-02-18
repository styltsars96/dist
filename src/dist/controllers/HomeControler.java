package dist.controllers;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

import dist.entities.Employee;
import dist.entities.Mechanic;
import dist.service.InternalUserService;

@Controller
public class HomeControler {
	
	@Autowired
	private InternalUserService userService;
	
	
	@GetMapping("/")
	public String home(HttpServletResponse response, Authentication authentication,Model model) {
		model.addAttribute("pageTitle", "Login");
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletResponse response, Authentication authentication, Model model) {
		String auth = authentication.getAuthorities().iterator().next().getAuthority();//Get Role
		String Uname = authentication.getName();//Get UserName
		if( auth.equals("employee")) {
			try {
				Employee empl = userService.getEmployee(Uname);
				System.out.println("HomeController: Redirect to Employee");//DEBUG
				model.addAttribute("employee", empl);
				response.addCookie( new Cookie("shopId",empl.getShop_id()+"") );
				response.addCookie(new Cookie("fullName",empl.getFullname()));
				model.addAttribute("pageTitle","Employee");
				return "redirect:/employee/";
			}catch(Exception e) {
				System.out.println("Invalid Employee");
				return "redirect:/403";
			}
		}else if(auth.equals("mechanic")){
			try {
				System.out.println("HomeController: Redirect to Mechanic");//DEBUG
				Mechanic mech = userService.getMechanic(Uname);
				model.addAttribute("mechanic", mech);
				response.addCookie( new Cookie("shopId",mech.getShop_id()+"") );
				response.addCookie(new Cookie("fullName",mech.getFullname()));
				model.addAttribute("pageTitle","Mechanic");
				return "redirect:/mechanic/";
			}catch(Exception e) {
				System.out.println("Invalid Mechanic");
				return "redirect:/403";
			}
		}else if(auth.equals("admin")){
			return "redirect:/user/list";
		}else {
			System.out.println("HomeControllerError!!!");//DEBUG
			model.addAttribute("pageTitle","Login Failure");
			return "redirect:/";
		}
	}

}
