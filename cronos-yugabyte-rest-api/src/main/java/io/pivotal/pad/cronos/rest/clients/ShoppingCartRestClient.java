package io.pivotal.pad.cronos.rest.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cronos-checkout-api")
@RequestMapping("/cronos-checkout-api")
public interface ShoppingCartRestClient {
	
	@RequestMapping("/shoppingCart/addProduct")
	String addProductToCart(@RequestParam("userid") String userId, 
			@RequestParam("asin") String asin);

}