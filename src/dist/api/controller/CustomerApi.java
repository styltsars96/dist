package dist.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dist.entities.Customer;
import dist.service.MainService;

@RestController
@RequestMapping("/api/customer")
public class CustomerApi {
	
	@Autowired
    private MainService mainService;
	

	@PostMapping(value="/login", produces="application/json;charset=utf-8")
	public Customer login(@RequestParam("username") String username,@RequestParam("password") String password) {
		System.out.println(username+password);
		Customer customer=mainService.loginCustomer(username, password);
		System.out.println("App Server"+customer);
		return customer;
	}
	
		
	@RequestMapping(value = "/{afm}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public Customer getCustomer(@PathVariable("afm") long afm) {

            Customer customer = mainService.getCustomerByAfm(afm);
            System.out.println("customer :" + customer);
           
            return customer;
    }
	
	@PostMapping(value="/new", produces = "application/json;charset=utf-8")
	public Customer newCustomer (@RequestBody  Customer customer ) {
		System.out.println("APP SERVER: I will save this: "+customer.toString());
		mainService.updateCustomer(customer);
		return customer;
	}
	
//	@Autowired    USELESS...
//	private CustomerList customerList;
	
	
//	@GetMapping(value="/all",produces= {"application/json"}) USELESS...
//	public CustomerList getallCustomer(){
//		List<Customer> customers = mainService.getCustomers();
//        System.out.println("customers :" + customers);
//        this.customerList.setCustomerList(customers);
//        return this.customerList;
//	}
}
