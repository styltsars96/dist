package dist.service;

import dist.entities.Shop;

public interface LocationService {
	public Integer calculateDistanceKM(double lon, double lat, Shop shop);
	public Shop getClosestShop(double lon, double lat);
	public boolean isDeliverable(double lon, double lat);
	public boolean isDeliverable(double lon, double lat, Shop shop);
}