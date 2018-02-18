package dist.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dist.dao.EmployeeDAO;
import dist.dao.InternalUserDAO;
import dist.dao.MechanicDAO;
import dist.dao.ShopDAO;
import dist.entities.Employee;
import dist.entities.InternalUser;
import dist.entities.Mechanic;
import dist.entities.Shop;

@Service
public class InternalUserServiceImpl implements InternalUserService {

	@Autowired
	private InternalUserDAO internalUserDAO;
	
	@Autowired
	private MechanicDAO mechanicDAO;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private ShopDAO shopDAO;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	@Override
	@Transactional
	public void saveInternalUser(InternalUser user) {
		// enable by default the user
		user.setEnabled((short) 1);
		// hash the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		internalUserDAO.save(user);
	}
	
	@Override
	@Transactional
	public void updateUserStatus(InternalUser user, short enabled) {
		user.setEnabled(enabled);
		internalUserDAO.save(user);
	}
	
	@Override
	@Transactional
	public void saveRole(InternalUser user, String role) {
		user.setShopRole(role);//Change the Role!!!
		internalUserDAO.save(user);
	}
	
	@Override
	@Transactional
	public InternalUser getUser(String name, String password) {
		return internalUserDAO.getUser(name, password);
	}
	
	@Override
	@Transactional
	public List<InternalUser> getUsers(){
		return internalUserDAO.getInternalUsers();
	}
	
	@Override
	@Transactional
	public Object getUserEntity(InternalUser user) {
		if(user.getShopRole().equals("mechanic")) {
			Mechanic mechanic =  mechanicDAO.getMechanic(user.getFullname(), user.getPassword());
			mechanic.setShop(shopDAO.getShop(mechanic.getShop_id()));
			return mechanic;
		}else if (user.getShopRole().equals("employee")) {
			Employee employee =  employeeDAO.getEmployee(user.getFullname(), user.getPassword());
			employee.setShop(shopDAO.getShop(employee.getShop_id()));
			return employee;
		}
		return null;
	}

	@Override
	@Transactional
	public Employee getEmployee(String name) {
		Employee employee =  employeeDAO.getEmployee(name,null);
		employee.setShop(shopDAO.getShop(employee.getShop_id()));
		return employee;
	}
	
	@Override
	@Transactional
	public Mechanic getMechanic(String name) {
		Mechanic mechanic =  mechanicDAO.getMechanic(name,null);
		mechanic.setShop(shopDAO.getShop(mechanic.getShop_id()));
		return mechanic;
	}
	
	@Override
	@Transactional
	public Mechanic getMechanic(String name, String password) {
		Mechanic mechanic =  mechanicDAO.getMechanic(name,password);
		mechanic.setShop(shopDAO.getShop(mechanic.getShop_id()));
		return mechanic;
	}
	
	@Override
	@Transactional
	public Employee getEmployee(String name, String password) {
		Employee employee =  employeeDAO.getEmployee(name,password);
		employee.setShop(shopDAO.getShop(employee.getShop_id()));
		return employee;
	}
	
	
	
	
	//TODO: Implement or Delete the rest...
	@Override
	@Transactional
	public void saveInternalUser(String name, String password, Shop shop) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Mechanic> getMechanics() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
