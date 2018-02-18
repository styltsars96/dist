package dist.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import dist.entities.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

	    Session currentSession = sessionFactory.getCurrentSession();

	    Query<Customer> query = currentSession.createQuery("from Customer", Customer.class);

	    List<Customer> customers = query.getResultList();
	    return customers;
	}

	@Override
	public void save(Customer customer) {
	    Session currentSession = sessionFactory.getCurrentSession();
	    System.out.println("customer id ---> " + customer.getId());

	    Query<Customer> query =currentSession.createQuery("from Customer c where c.afm = "+customer.getAfm(),Customer.class);

	    if (!query.getResultList().isEmpty()) { //not new...
	        //get the customer
	            customer.setId(query.getResultList().get(0).getId());
	            System.out.println("This customer has been signed in!"+customer);
	            Customer dbcustomer = query.getSingleResult();

	            if(dbcustomer.getPass() == null) {
	                dbcustomer.setFullname(customer.getFullname());
	                dbcustomer.setPass(customer.getPass());
	                currentSession.save(dbcustomer);//update customer to insert fullName, password.
	                System.out.println("AppServer: Customer name, password has been changed.");
	            }
	    }else {//new...
	            // save the customer
	        currentSession.save(customer);

	    }
	}

	@Override
	public List<Customer> getCustomersByAttribute(String attrName, Object value) {
	    Session currentSession = sessionFactory.getCurrentSession();
	    Query<Customer> query = null;
	    switch(attrName) {
	    case "id":
	        value = (Integer) value;
	        query = currentSession.createQuery("from Customer C WHERE C.id = "+ value , Customer.class);
	        break;
	    case "fullname":
	        value = (String) value;
	        query = currentSession.createQuery("from Customer C WHERE C.fullname = '" + value +"'", Customer.class);
	        break;
	    case "afm":
	        value = (Long) value;
	        query = currentSession.createQuery("from Customer C WHERE C.afm = " + value, Customer.class);
	        break;
	    case "telephone":
	        value = (Long) value;
	        query = currentSession.createQuery("from Customer C WHERE C.telephone = "+ value , Customer.class);
	        break;
	    default:
	        System.out.println("getCustomersByAttribute: Wrong Attribute");
	        return null;
	    }
	    List<Customer> customers = query.getResultList();
	    return customers;
	}

	@Override
	public Customer getCustomerById(int id) {
	    Session currentSession = sessionFactory.getCurrentSession();
	    Query<Customer> query =currentSession.createQuery("from Customer c where c.id = "+id,Customer.class);

	    return query.getSingleResult();
	}

	//Customer Login not tested!
	@Override
	public Customer loginCustomer(String username, String password) {
	    Session currentSession =  sessionFactory.getCurrentSession();
	    try {
	        Query<Customer> query =currentSession.createQuery("from Customer where fullname= '"+username+"' and passcode = '"+password+"'",Customer.class);
	        return query.getSingleResult();
	    }catch (Exception e){ 
	        System.out.println("Wrong at DAO loginCustomer.");
	        return null;
	    }
	}
}
