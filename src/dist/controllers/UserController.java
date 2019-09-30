package dist.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dist.entities.InternalUser;
import dist.service.InternalUserService;
//ADMIN
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private InternalUserService userService;
	
	@GetMapping("/list")
	public String listUsers(Model model) {
		// get all users from the service
		List<InternalUser> users = userService.getUsers();
		// add all users to the model
		model.addAttribute("users",users);
		// add page title
		model.addAttribute("pageTitle", "GodMode: List Users");
		return "admin/adminHome";
	}
	
	@GetMapping("/showAddForm")
	public String showAddForm(Model model) {
		// create model attribute to get form data
		InternalUser user = new InternalUser();
		model.addAttribute("user", user);
		// add page title
		model.addAttribute("pageTitle", "GodMode: Add User");
		return "admin/user-form";
	}
	
	@PostMapping("/saveUser") public String saveUser(@ModelAttribute("user") InternalUser user,Model model) { // save the User using the service 
		userService.saveInternalUser(user);
		System.err.println(user.getFullname());
		model.addAttribute("username",user.getFullname());
		return "redirect:/user/assignRole/"; 
	}
	
	@GetMapping("/enable/{username}")
	public String enableUser(Model model,  @PathVariable("username") String username, Authentication authentication) {
		String auth = authentication.getAuthorities().iterator().next().getAuthority();//Get Role
		if(auth.equals("admin")) {//Check if admin
			InternalUser user = userService.getUser(username, null);
			if(user.getEnabled()==(short) 1 ) {
				System.out.println("Disabling: " + user);
				userService.updateUserStatus(user, (short) 0);
			}else if (user.getEnabled()==(short) 0) {
				System.out.println("Disabling: " + user);
				userService.updateUserStatus(user, (short) 1);
			}
			return "redirect:/user/list";
		}else return "redirect:/403";
	}
	
	@GetMapping("/modify/{username}")
	public String modifyUser(Model model,  @PathVariable("username") String username, Authentication authentication) {
		String auth = authentication.getAuthorities().iterator().next().getAuthority();//Get Role
		if(auth.equals("admin")) {//Check if admin
			InternalUser user = userService.getUser(username, null);
		    model.addAttribute("user", user);
			return "admin/user-update";
		}else return "redirect:/403";
	}
	
	@PostMapping("/modify/{username}/update")
	public String modifyInternalUser(Model model,@PathVariable("username") String username, @ModelAttribute("newProcess") InternalUser user,  Authentication authentication) {
		String auth = authentication.getAuthorities().iterator().next().getAuthority();//Get Role
		try {
			InternalUser updUser = userService.getUser(username, null);
			if(auth.equals("admin")) {//Check if admin
				System.out.println("Updating : " + updUser);
				if(userService.getUser(user.getFullname(),null)!=null) {
					if(!username.equals(user.getFullname())) return "redirect:/500";
				}//If someone puts same usernamea as another user, then error!
				updUser.setFullname(user.getFullname());
				updUser.setPassword(user.getPassword());
				System.out.println(user.getShopId());
				updUser.setShopId(user.getShopId());
				userService.saveInternalUser(updUser);
				return "redirect:/user/list";
			}else return "redirect:/403";
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return "redirect:/500";
		}
	}
		
	@GetMapping("/assignRole/") public String assignRole(Model model, Authentication authentication,@ModelAttribute("username") String username ) { 
	String auth = authentication.getAuthorities().iterator().next().getAuthority();//Get Role 
	if(auth.equals("admin")) {//Check if admin 
	InternalUser user = userService.getUser(username, null);
	List<String> roles = new ArrayList<>(); //AVAILABLE ROLES!!!
	roles.add("admin");
	roles.add("mechanic");
	roles.add("employee");
	model.addAttribute("roles", roles); model.addAttribute("user", user); return "admin/assign-role"; }else return "redirect:/403"; 
	}
	
	@PostMapping("/assignRole/{username}")
	public String assignRoleToUser(@PathVariable("username") String username, @RequestParam("role") String role, Authentication authentication) {
		String auth = authentication.getAuthorities().iterator().next().getAuthority();//Get Role
		if(auth.equals("admin")) {//Check if admin
			InternalUser user = userService.getUser(username, null);
			System.out.println("Role : " + role);
			userService.saveRole(user, role);
			return "redirect:/user/list";
		}else return "redirect:/403";
		
	}


}