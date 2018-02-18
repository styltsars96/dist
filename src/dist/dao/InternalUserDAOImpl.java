package dist.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dist.entities.InternalUser;

@Repository
public class InternalUserDAOImpl implements InternalUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<InternalUser> getInternalUsers() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query <InternalUser> query = currentSession.createQuery("from InternalUser order by fullname", InternalUser.class);
		
		List<InternalUser> internalUsers = query.getResultList(); 
		
		return internalUsers;
	}

	@Override
	public InternalUser getUser(String name, String password) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <InternalUser> query;
		InternalUser user;
		if(password == null) {
			query = currentSession.createQuery("from InternalUser where fullname= '"+name+"'" , InternalUser.class);
			user = query.uniqueResult();
			return user;
		}else{
			query = currentSession.createQuery("from InternalUser where fullname= '"+name+"' and password= '"+password+"'" , InternalUser.class);
			user = query.uniqueResult();
			return user;
		}
	}

	@Override
	public void save(InternalUser user) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		System.out.println("User id ---> " + user.getId());
		if (user.getId() != 0) {
			currentSession.update(user);
		}else{
			currentSession.save(user);
		}
		currentSession.save(user);
		
		
	}

	@Override
	public void update(InternalUser user) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.update(user);
		
		
	}
	
}
