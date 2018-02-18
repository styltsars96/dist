package dist.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dist.dao.ShopDAO;
import dist.entities.Shop;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	ShopDAO shopDao;
	
	/*
	 * Calculate distance in KM to shop
	*/
	@Override
	@Transactional
	public Integer calculateDistanceKM(double lon, double lat, Shop shop) {
			if (shop == null) return -1;
			double latS = shop.getLat();
			double lonS = shop.getLon();
			Double dist_d = distance(lat, lon, latS, lonS, 'K');
			return Math.abs(dist_d.intValue());
	}
	
	/*
	 * Get the closest shop to a location
	 */
	@Override
	@Transactional
	public Shop getClosestShop(double lon, double lat) {
		List<Shop> shopList = shopDao.getShops();
		Shop closestShop = null;
		int min = Integer.MAX_VALUE;
		for(Shop shop : shopList) {
			int temp = calculateDistanceKM(lon, lat, shop);
			if (temp < min) {
				min = temp;
				closestShop = shop;
			}
		}
		return closestShop;
	}

	/*
	 * Find if car is deliverable to closest shop
	 */
	@Override
	@Transactional
	public boolean isDeliverable(double lon, double lat) {
		return isDeliverable(lon, lat, getClosestShop(lon, lat));
	}
	
	/*
	 * Find if car is deliverable to shop 
	 */
	@Override
	@Transactional
	public boolean isDeliverable(double lon, double lat, Shop shop) {
		int distance = this.calculateDistanceKM(lon, lat, shop);
		if( distance > 0 && distance <= 100 ) {
			return true;
		}else if (distance > 100) {
			return false;
		} else {
			System.out.println("isDeliverable: Invalid Distance:" + distance);
			return false;
		}
	}
	
	/*
	 * Calculates the exact distance between coordinates.
	 */
	private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == 'K') {
			dist = dist * 1.609344;
		} else if (unit == 'N') {
			dist = dist * 0.8684;
		}
		return (dist);
	}

	/*
	 *  This function converts decimal degrees to radians.
	 */
	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*
	 * This function converts radians to decimal degrees.
	 */
	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}


}
