package dist.dao;

import java.util.List;

import dist.entities.Car;

public interface CarDAO {

	public List<Car> getCars();
	
	public void saveCar(Car car);
	
	public Car getCarByPlate(String plate);
	
	public List<Car> getCarsByCustomer(String name);
}
