package dist.dao;

import java.util.List;

import dist.entities.Employee;

public interface EmployeeDAO {

	public List<Employee> getEmployees();//Useless

	public Employee getEmployee(String name, String password);//LOGIN
		
}
