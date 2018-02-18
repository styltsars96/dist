package dist.dao;

import java.util.List;

import dist.entities.Mechanic;

public interface MechanicDAO {

	public List<Mechanic> getMechanics();
	
	public Mechanic getMechanic(String name, String password);//LOGIN
}
