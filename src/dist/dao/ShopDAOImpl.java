package dist.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dist.entities.Shop;

@Repository
public class ShopDAOImpl implements ShopDAO {

	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<Shop> getShops() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <Shop> query =currentSession.createQuery("from Shop", Shop.class); //ALL EVERYWHERE
		List<Shop> shops=query.getResultList();
		return shops;
	}

	@Override
	public Shop getShop(double lon, double lat) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <Shop> query =currentSession.createQuery("from Shop s WHERE s.lon="+lon+ "AND s.lat="+lat, Shop.class);
		Shop shop = query.uniqueResult();
		return shop;
	}

	@Override
	public Shop getShop(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query <Shop> query =currentSession.createQuery("from Shop s WHERE s.id="+id, Shop.class);
		Shop shop = query.uniqueResult();
		return shop;
	}

}
