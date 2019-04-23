package io.pivotal.pad.cronos.service;

import java.util.Map;

public interface ShoppingCartServiceRest {
	
	String addProduct(String userId, String product);
	Map<String, Integer> getProductsInCart(String userId);

}
