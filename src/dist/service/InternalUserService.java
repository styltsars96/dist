package dist.service;

import java.util.List;

import dist.entities.Employee;
import dist.entities.InternalUser;
import dist.entities.Mechanic;
import dist.entities.Shop;

public interface InternalUserService {

	public InternalUser getUser(String name, String password);
	public Object getUserEntity(InternalUser user);
	public void saveInternalUser(String name, String password,Shop shop);
	public void saveInternalUser(InternalUser user);
	public List<Employee> getEmployees();
	public Employee getEmployee(String name);
	public Employee getEmployee(String name, String password);
	public List<Mechanic> getMechanics();
	public Mechanic getMechanic(String name);
	public Mechanic getMechanic(String name, String password);
	public List<InternalUser> getUsers();
	public void saveRole(InternalUser user, String role);
	public void updateUserStatus(InternalUser user, short enabled);
	
}
