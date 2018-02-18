package dist.dao;

import java.util.List;

import dist.entities.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();//Useless...
	
	public List<Customer> getCustomersByAttribute(String attrName, Object value );//Get customers by searching for a value.

	public void save(Customer customer);
	
	//for login
	public Customer loginCustomer(String username, String password);
	
	//for Api
	public Customer getCustomerById(int id);
}
