package io.pivotal.pad.cronos.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import io.pivotal.pad.cronos.rest.clients.ShoppingCartRestClient;
import io.pivotal.pad.cronos.service.ShoppingCartServiceRest;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartServiceRestImpl implements ShoppingCartServiceRest {
	
	private final ShoppingCartRestClient shoppingCartRestClient;
	
	@Autowired
	public ShoppingCartServiceRestImpl(ShoppingCartRestClient shoppingCartRestClient) {
		this.shoppingCartRestClient = shoppingCartRestClient;
	}

	@Override
	public String addProduct(String userId, String product) {
		
		String result = shoppingCartRestClient.addProductToCart(userId, product);
		return result;
	}

	@Override
	public Map<String, Integer> getProductsInCart(String userId) {
		
		Map<String, Integer> productsInCart = shoppingCartRestClient.getProductsInCart(userId);
		return productsInCart;
	}

}
