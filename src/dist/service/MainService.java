package dist.service;

import java.util.List;

import dist.entities.Customer;
import dist.entities.Delivery;
import dist.entities.Process;
import dist.entities.Shop;
import dist.entities.Car;

public interface MainService {
	
	//For Customers
	public List<Customer> getCustomers();
	
	public void updateCustomer(Customer customer);
	
	public Customer getCustomerByAfm(Long afm);
	
	//For Api
	public Customer getCustomerById(int id);
	
	//For car for api
	public Car getCarByPlate(String plate);
	
	public List<Car> getCarsByCustomer(String  name);
	
	
	//For Processes
	public List<Process> getProcesses();
	
	public List<Process> getProcesses(int shop_id,String status);
//	public List<Process> getCompletedProcesses();
//	public List<Process> getProcessesByShop(int shop_id);
	
	public List<Process> getProcessesByCustomer(String customerAfm);
	
	public Process getProcessById(int id);
	
	public Process getProcessByPlate(String Plate);
	
	public void saveProcess(Process process);
	
	public void updateProcess(Process process);
	
	
	//For Shop
	public Shop getShopById(int id);
	//For api
	public List<Shop> getShops();
	
	
	//for customer login
	public Customer loginCustomer(String username, String password);
	
	
	//for delivered cars
	public List<Delivery> getDeliverCars();
	
	public List<Delivery> getDeliverCarsByCustomer(String customerName);
	
	public Delivery getDeliveryCarByPlate(String plate);
	
	public List<Delivery> getDeliverCarsByStatus(String status,int shopId);
	
	public void saveDelivery(Delivery delivery);
	
	public void updateDelivery (Delivery delivery);
}