package dist.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dist.entities.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Employee> getEmployees() {
		Session currentSession = sessionFactory.getCurrentSession();
        
        Query <Employee> query = currentSession.createQuery("from Employee", Employee.class);
        
        List<Employee> employees = query.getResultList();
                        
        return employees;
	}

	@Override
	public Employee getEmployee(String name,String password) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <Employee> query;
		if(password != null) {
			query = currentSession.createQuery("from Employee where fullname= '"+name+"' and password= '"+password+"'", Employee.class);
		}else {
			query = currentSession.createQuery("from Employee where fullname= '"+name+"'", Employee.class);
		}
		
		Employee employee = query.uniqueResult();
		return employee;
	}

	
}
