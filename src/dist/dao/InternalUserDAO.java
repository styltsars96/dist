package dist.dao;

import java.util.List;

import dist.entities.InternalUser;

public interface InternalUserDAO {

	public List<InternalUser> getInternalUsers();
	
	public InternalUser getUser(String name,String password);
	
	public void update(InternalUser user);
	
	public void save(InternalUser user);
}
