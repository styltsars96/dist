package dist.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dist.dao.CarDAO;
import dist.dao.CustomerDAO;
import dist.dao.DeliveryDAO;
import dist.dao.ProcessDAO;
import dist.dao.ShopDAO;
import dist.entities.Car;
import dist.entities.Customer;
import dist.entities.Delivery;
import dist.entities.Process;
import dist.entities.Shop;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	ProcessDAO processDAO;
	
	@Autowired
	CarDAO carDAO;
	
	@Autowired
	ShopDAO shopDAO;
	
	@Autowired
	DeliveryDAO deliveryDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		customerDAO.save(customer);
	}

	@Override
	@Transactional
	public Customer getCustomerByAfm(Long afm) {
		return customerDAO.getCustomersByAttribute("afm", afm).get(0);
	}
	
	@Override
	@Transactional
	public Customer getCustomerById(int id) {
		return customerDAO.getCustomerById(id);
	};
	
	@Override
	@Transactional
	public Car getCarByPlate(String plate) {
		return carDAO.getCarByPlate(plate);
	}
	
	@Override
	@Transactional
	public List<Car> getCarsByCustomer(String name) {
		return carDAO.getCarsByCustomer(name);
	}
	
	@Override
	@Transactional
	public List<Process> getProcesses() {
		return processDAO.getProcesses();
	}
	
	@Override
	@Transactional
	public List<Process> getProcesses(int shop_id, String status) {
		return processDAO.getProcesses(shop_id,status);
	}
	
	@Override
	@Transactional
	public List<Process> getProcessesByCustomer(String customerAfm) {
		return processDAO.getProcessesByCustomer(customerAfm);
	}

	@Override
	@Transactional
	public Process getProcessById(int id) {
		return processDAO.getProcessById(id);
	}
	
	@Override
	@Transactional
	public Process getProcessByPlate(String plate) {
		return processDAO.getProcessByPlate(plate);
	}
	
	@Override
	@Transactional
	public void saveProcess(Process process) {	
		//BE SURE THAT NO MATTER WHAT, ALL ARE SAVED!!!
		Car car = process.getCar();

		//customerDAO.save(car.getCustomer());
		carDAO.saveCar(car);
		processDAO.saveProcess(process);
	}


	@Override
	@Transactional
	public void updateProcess(Process process) {//It does NOT DO HARD SAVE.
		processDAO.updateProcess(process);
	}
	
	
	@Override
	@Transactional
	public Shop getShopById(int id) {
		return shopDAO.getShop(id);
	}


	@Override
	@Transactional
	public Customer loginCustomer(String username, String password) {
		return customerDAO.loginCustomer(username, password);
	}

	@Override
	@Transactional
	public List<Delivery> getDeliverCars(){
		return deliveryDAO.getDeliverCars();
	}
	
	@Override
	@Transactional
	public List<Delivery> getDeliverCarsByCustomer(String name){
		return deliveryDAO.getDeliverCarsByCustomer(name);
	}
	@Override
	@Transactional
	public Delivery getDeliveryCarByPlate(String plate) {
		return deliveryDAO.getDeliveryCarByPlate(plate);
	}
	
	@Override
	@Transactional
	public List<Delivery> getDeliverCarsByStatus(String status,int shopId){
		return deliveryDAO.getDeliverCarsByStatus(status,shopId);
	}
	
	@Override
	@Transactional
	public void saveDelivery(Delivery delivery) {
		deliveryDAO.saveDelivery(delivery);
	}
	
	@Override
	@Transactional
	public void updateDelivery(Delivery delivery) {
		deliveryDAO.updateDelivery(delivery);
	}
	
	@Override
	@Transactional
	public List<Shop> getShops() {
		return shopDAO.getShops();
	}
	
}
