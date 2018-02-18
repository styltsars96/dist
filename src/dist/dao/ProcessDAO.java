package dist.dao;

import java.util.List;
import dist.entities.Process;
//import dist.entities.Shop;

public interface ProcessDAO {

	public List<Process> getProcesses();
	
	public List<Process> getProcesses(int shop_id,String status);//Maybe (Shop shop,String status)
	
	public List<Process> getProcessesByCustomer(String customerName);
	
	public Process getProcessById(int id);
	
	public Process getProcessByPlate(String plate);
	
	public void saveProcess(Process process);
	
	public void updateProcess(Process process);	
}
