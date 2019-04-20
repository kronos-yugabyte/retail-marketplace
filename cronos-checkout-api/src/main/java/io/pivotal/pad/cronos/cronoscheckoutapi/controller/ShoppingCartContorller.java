package io.pivotal.pad.cronos.cronoscheckoutapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.pad.cronos.cronoscheckoutapi.service.OrderCheckoutService;

@RestController
@RequestMapping(value = "/cronos-checkout-api")
public class ShoppingCartContorller {
	
	@Autowired
	OrderCheckoutService orderCheckoutService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/shoppingCart/addProduct", produces = "application/json")
	public String addProductToCart(@RequestParam("userid") String userId, 
			@RequestParam("asin") String asin) {
//		productService.findById(asin).ifPresent(shoppingCartService::addProduct);

		orderCheckoutService.addProductToShoppingCart(userId, asin);
		return String.format("Added to Cart");
	}

}
