package dist.dao;

import java.util.List;
import dist.entities.Shop;

public interface ShopDAO {

	public List<Shop> getShops();
	public Shop getShop(double lon, double lat );
	public Shop getShop(int id );
	
}
