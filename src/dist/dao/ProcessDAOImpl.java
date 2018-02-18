package dist.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dist.entities.Process;
//import dist.entities.Shop;

@Repository
public class ProcessDAOImpl implements ProcessDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Process> getProcesses() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <Process> query =currentSession.createQuery("from process as p order by date(p.statusDate)", Process.class); //ALL EVERYWHERE
		List<Process> processes=query.getResultList();
		return processes;
	}
	
	@Override
	public List<Process> getProcesses(int shop_id, String status) {
		Session currentSession = sessionFactory.getCurrentSession();
        Query<Process> query = currentSession.createQuery("from Process as p where p.shop.id="+shop_id+" and p.status='"+status+"' order by date(p.statusDate)", Process.class);
        List<Process> processes=query.getResultList();
		System.out.println("getProcesses with status="+status+" shop_id="+shop_id+" : "+processes);
		return processes;
	}
	
	@Override
	public List<Process> getProcessesByCustomer(String customerAfm){
		Session currentSession = sessionFactory.getCurrentSession();
        Query<Process> query = currentSession.createQuery("from Process as p where p.car.customer.afm='"+customerAfm+"'", Process.class);
        List<Process> processes=query.getResultList();
		System.out.println("getProcesses with customer's afm="+customerAfm+" : "+processes);
		return processes;
	}
	
	@Override
	public Process getProcessById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
        Query<Process> query = currentSession.createQuery("from Process where id= "+id, Process.class);
        return query.uniqueResult();
	}
	
	@Override
	public Process getProcessByPlate(String plate) {
		Session currentSession = sessionFactory.getCurrentSession();
        Query<Process> query = currentSession.createQuery("from Process p where p.car.licencePlate= "+plate, Process.class);
        return query.uniqueResult();
	}

	@Override
	public void saveProcess(Process process) {
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println("Process id ---> " + process.getId());
		if (process.getId() != 0) {
			currentSession.update(process);
		}else{
			currentSession.save(process);
		}
		currentSession.save(process);
	}
	


	@Override //Just for back compatibility
	public void updateProcess(Process process) {
		saveProcess(process);
	}

}