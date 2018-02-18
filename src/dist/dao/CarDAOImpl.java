package dist.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dist.entities.Car;


@Repository
public class CarDAOImpl implements CarDAO{

	@Autowired
	SessionFactory sessionFactory; 
	
	@Autowired
	CustomerDAO customerDAO;
	
	@Override
	public List<Car> getCars() {

        Session currentSession = sessionFactory.getCurrentSession();
        
        Query<Car> query = currentSession.createQuery("from Car", Car.class);
        
        List<Car> cars = query.getResultList();
        return cars;
	}


	@Override
	public void saveCar(Car car) {
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println("car id ---> " + car.getId());
		
		if (car.getId() != 0) {
		// update the car
			currentSession.update(car);
		}
		else {
			//save the customer
			if(car.getCustomer().getId()==0) customerDAO.save(car.getCustomer());
			// save the car
			currentSession.save(car);
		}
	}
	
	//For api
	@Override
	public Car getCarByPlate(String plate) {
        Session currentSession = sessionFactory.getCurrentSession();
        try {
        Query<Car> query = currentSession.createQuery("from Car c where c.licencePlate='"+plate+"'", Car.class);
        return query.getSingleResult();
        }catch(Exception e) {
        	return null;
        }
	}
	
	@Override
	public List<Car> getCarsByCustomer(String name) {
	    Session currentSession = sessionFactory.getCurrentSession();
	    Query<Car> query = currentSession.createQuery("from Car where customer.fullname ='"+ name+"'", Car.class);
	    List<Car> cars = query.getResultList();
	    return cars;
	}
}