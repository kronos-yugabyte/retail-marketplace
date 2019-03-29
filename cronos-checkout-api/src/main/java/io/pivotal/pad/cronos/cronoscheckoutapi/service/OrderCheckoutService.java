package io.pivotal.pad.cronos.cronoscheckoutapi.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.pivotal.pad.cronos.cronoscheckoutapi.domain.ShoppingCart;
import io.pivotal.pad.cronos.cronoscheckoutapi.domain.ShoppingCartKey;
import io.pivotal.pad.cronos.cronoscheckoutapi.repositories.OrderRepository;
import io.pivotal.pad.cronos.cronoscheckoutapi.repositories.ProductInventoryRepository;
import io.pivotal.pad.cronos.cronoscheckoutapi.repositories.ShoppingCartRepository;

@Service
public class OrderCheckoutService {
	
	private static final int DEFAULT_QUANTITY = 1;
	private final OrderRepository orderRepository;
	private final ProductInventoryRepository productInventoryRepository;
	private final ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	public OrderCheckoutService(OrderRepository orderRepository, 
			ProductInventoryRepository productInventoryRepository, ShoppingCartRepository shoppingCartRepository) {
		this.orderRepository = orderRepository;
		this.productInventoryRepository = productInventoryRepository;
		this.shoppingCartRepository = shoppingCartRepository;
	}
	
	/**
	 * If product is in the map just increment quantity by 1. If product is not in
	 * the map with, add it with quantity 1
	 *
	 * @param product
	 */
	public void addProductToShoppingCart(String userId, String asin) {
		
		
		ShoppingCartKey currentKey = new ShoppingCartKey(userId, asin);
		if (shoppingCartRepository.findById(currentKey).isPresent()) {
			shoppingCartRepository.updateQuantityForShoppingCart(userId, asin);
			System.out.println("Adding product: " + asin);
		} else {
			ShoppingCart currentShoppingCart = createCartObject(currentKey);
			shoppingCartRepository.save(currentShoppingCart);
			System.out.println("Adding product: " + asin);
		}
	}
	
	private ShoppingCart createCartObject(ShoppingCartKey currentKey) {
		ShoppingCart currentShoppingCart = new ShoppingCart();
		currentShoppingCart.setShoppingCartKey(currentKey);
		LocalDateTime currentTime = LocalDateTime.now();
		currentShoppingCart.setTime_added(currentTime.toString());
		currentShoppingCart.setQuantity(DEFAULT_QUANTITY);
		
		return currentShoppingCart;
	}
	
	public void checkout() {
		
	}
	
	
}