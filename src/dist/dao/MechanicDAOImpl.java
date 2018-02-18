package dist.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dist.entities.Mechanic;

@Repository
public class MechanicDAOImpl implements MechanicDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Mechanic> getMechanics() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <Mechanic> query = currentSession.createQuery("from Mechanic", Mechanic.class);        
        List<Mechanic> mechanics = query.getResultList();
        
		return mechanics;
	}

	@Override
	public Mechanic getMechanic(String name, String password) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <Mechanic> query;
		if(password != null) {
			query = currentSession.createQuery("from Mechanic where fullname= '"+name+"' and password= '"+password+"'", Mechanic.class);
		}else {
			query = currentSession.createQuery("from Mechanic where fullname= '"+name+"'", Mechanic.class);
		}
		
		
		Mechanic mechanic = query.uniqueResult();
		return mechanic;
	}
	
}
