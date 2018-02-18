package dist.dao;

import java.util.List;

import dist.entities.Delivery;

public interface DeliveryDAO {

	public List<Delivery> getDeliverCars();
	
	public List<Delivery> getDeliverCarsByCustomer(String customerName);
	
	public Delivery getDeliveryCarByPlate(String plate);
	
	public List<Delivery> getDeliverCarsByStatus(String status, int shopId);
	
	public void saveDelivery(Delivery delivery);
	
	public void updateDelivery(Delivery delivery);
}
