package dist.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dist.entities.Delivery;

@Repository
public class DeliveryDAOImpl implements DeliveryDAO{

	@Autowired
	SessionFactory sessionFactory; 
	
	@Override
	public List<Delivery> getDeliverCars() {

        Session currentSession = sessionFactory.getCurrentSession();
        
        Query<Delivery> query = currentSession.createQuery("from Delivery", Delivery.class);
        
        return query.getResultList();
        
	}
	
	@Override
	public List<Delivery> getDeliverCarsByCustomer(String customerName){
		
		 Session currentSession = sessionFactory.getCurrentSession();
	        
	     Query<Delivery> query = currentSession.createQuery("from Delivery where process.car.customer.fullname='"+customerName+"'", Delivery.class);
		
		return query.getResultList();
	}
	
	@Override
	public Delivery getDeliveryCarByPlate(String plate){
		Session currentSession = sessionFactory.getCurrentSession();
        
	     Query<Delivery> query = currentSession.createQuery("from Delivery where process.car.licencePlate='"+plate+"'", Delivery.class);
		
		return query.getSingleResult();
	}
	
	@Override
	public List<Delivery> getDeliverCarsByStatus(String status, int shopId){
		System.err.println(status+" "+shopId);
		 Session currentSession = sessionFactory.getCurrentSession();
	        
	     Query<Delivery> query = currentSession.createQuery("from Delivery d where d.status='"+status+"' and d.process.shop.id="+shopId, Delivery.class);
		
		return query.getResultList();
	}
	
	@Override
	public void saveDelivery(Delivery delivery) {
		 Session currentSession = sessionFactory.getCurrentSession();
	     
	     currentSession.save(delivery);
	}
	
	@Override
	public void updateDelivery(Delivery delivery) {
		 Session currentSession = sessionFactory.getCurrentSession();
	     
	     currentSession.update(delivery);
	}
	
}
