package dist.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="ShopList")
@Component
public class ShopList {
	
	List<Shop> shopList;
	
	public List<Shop> getShopList(){
		return shopList;
	}
	
	public void setShopList(List<Shop> shopList) {
		this.shopList=shopList;
	}
}
