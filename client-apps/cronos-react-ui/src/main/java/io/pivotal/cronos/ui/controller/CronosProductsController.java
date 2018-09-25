package io.pivotal.cronos.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.cronos.ui.rest.DashboardRestConsumer;

@RestController
public class CronosProductsController {
	
	@Autowired
	DashboardRestConsumer dashboardRestConsumer;

	@GetMapping("/products")
	public String getProducts() {
		return dashboardRestConsumer.getHomePageProducts(10);
		//return "[{\"id\": 5,\"name\": \"How to Win Friends & Influence People\",\"description\": \"For more than sixty years the rock-solid\",\"price\": 9.6,\"author\": \"Dale Carnegie\",\"type\": \"paperback\",\"img\": \"https://images-na.ssl-images-amazon.com/images/I/51PWIy1rHUL._AA300_.jpg\",\"category\": \"business\",\"num_reviews\": 182,\"total_stars\": 550,\"stars\": \"3.02\"}]";
	}

  @RequestMapping(method = RequestMethod.GET, value = "/products/details")
  public @ResponseBody String getProductDetails(@RequestParam("asin") String asin) {
    return dashboardRestConsumer.getProductDetails(asin);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/cart/add")
	public @ResponseBody String addProductToCart(@RequestParam("asin") String asin) {
		return dashboardRestConsumer.addProductToCart(asin);
	}

  @RequestMapping(method = RequestMethod.GET, value = "/cart/remove")
	public @ResponseBody String removeProductFromCart(@RequestParam("asin") String asin) {
		return dashboardRestConsumer.removeProductFromCart(asin);
	}

  @RequestMapping(method = RequestMethod.GET, value = "/cart/get")
	public @ResponseBody String getCart() {
		return dashboardRestConsumer.getCart();
	}
}
