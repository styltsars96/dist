package dist.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import dist.entities.Shop;
import dist.entities.ShopList;
import dist.service.LocationService;
import dist.service.MainService;

@RestController
@RequestMapping("/api/shop")
public class ShopApi {
	
	@Autowired
    private MainService mainService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private ShopList shopList;
	
	
	@GetMapping(value="/all",produces= {"application/json;charset=utf-8"})
	public ShopList getallShops(){
		List<Shop> shops = mainService.getShops();
        System.out.println("shops :" + shops);
        this.shopList.setShopList(shops);
        return this.shopList;
	}
	
	@PostMapping(value = "/closest_shop", produces= "application/json")
	public Shop getDistance(@RequestParam("lat") String lattxt, @RequestParam("lon") String lontxt) {
		Double lon,lat;
		try {
			lon=Double.parseDouble(lontxt);
			lat=Double.parseDouble(lattxt); 
		}catch(Exception e) {
			return null; 
		} if(!locationService.isDeliverable(lon, lat)) {
			//If car cannot be delivered by crane (>100km) 
			return null; //Empty Response 
		} 
		return locationService.getClosestShop(lat,lon); 
	}
		
	
	
	@RequestMapping(value = "/is_deliverable", method = RequestMethod.GET, produces= {"application/json;charset=utf-8"})
	public String isDeliverable(@RequestParam("latitude") Double lat, @RequestParam("longitude") Double lon) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.createObjectNode();
		((ObjectNode) rootNode).put("isDeliverable", locationService.isDeliverable(lon, lat));
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "isDeliverable:Problem";
		}
	}
	
	
	@RequestMapping(value = "/is_deliverable", method = RequestMethod.POST, produces= {"application/json;charset=utf-8"})
	public String isDeliverableToShop(@RequestParam("latitude") Double lat, @RequestParam("longitude") Double lon, @RequestParam("shop_id") int id) {
		Shop shop = mainService.getShopById(id);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.createObjectNode();
		((ObjectNode) rootNode).put("isDeliverable", locationService.isDeliverable(lon, lat, shop));
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "isDeliverable:Problem";
		}
	}
	
	
}
