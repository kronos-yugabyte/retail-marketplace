package io.pivotal.pad.cronos.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.pad.cronos.domain.CheckoutStatus;
import io.pivotal.pad.cronos.domain.Order;
import io.pivotal.pad.cronos.exception.NotEnoughProductsInStockException;
import io.pivotal.pad.cronos.service.ShoppingCartService;
import io.pivotal.pad.cronos.service.ShoppingCartServiceRest;

@RestController
@RequestMapping(value = "/api/v1")
public class ShoppingCartController {

	private final ShoppingCartService shoppingCartService;
	
	private final ShoppingCartServiceRest shoppingCartServiceRest;

	@Autowired
	public ShoppingCartController(ShoppingCartService shoppingCartService, 
			ShoppingCartServiceRest shoppingCartServiceRest ) {
		this.shoppingCartService = shoppingCartService;
		this.shoppingCartServiceRest = shoppingCartServiceRest;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/shoppingCart", produces = "application/json")
	public @ResponseBody ResponseEntity<Map<String, Integer>> shoppingCart() {

		Map<String, Integer> productsInCart = shoppingCartService.getProductsInCart();

		if (productsInCart == null) {
			return new ResponseEntity<Map<String, Integer>>(productsInCart, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Integer>>(productsInCart, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/shoppingCart/addProduct", produces = "application/json")
	public ResponseEntity<?> addProductToCart(@RequestParam("asin") String asin) {
//		productService.findById(asin).ifPresent(shoppingCartService::addProduct);
		String userId = "u1001";
		shoppingCartServiceRest.addProduct(userId, asin);
		Map<String, Integer> productsInCart = shoppingCartServiceRest.getProductsInCart(userId);
//		shoppingCartService.addProduct(asin);
//		Map<String, Integer> productsInCart = shoppingCartService.getProductsInCart();
		
		if (productsInCart == null) {
			return new ResponseEntity<Map<String, Integer>>(productsInCart, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Integer>>(productsInCart, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/shoppingCart/removeProduct", produces = "application/json")
	public ResponseEntity<Map<String, Integer>> removeProductFromCart(@RequestParam("asin") String asin) {
//		productService.findById(asin).ifPresent(shoppingCartService::removeProduct);
		shoppingCartService.removeProduct(asin);
		Map<String, Integer> productsInCart = shoppingCartService.getProductsInCart();

		if (productsInCart == null) {
			return new ResponseEntity<Map<String, Integer>>(productsInCart, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Integer>>(productsInCart, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/shoppingCart/checkout", produces = "application/json")
	public ResponseEntity<CheckoutStatus> checkout() {
		CheckoutStatus checkoutStatus = new CheckoutStatus();
		try {
			Order currentOrder = shoppingCartService.checkout();
			if (currentOrder != null) {
				checkoutStatus.setOrderNumber(currentOrder.getId().toString());
				checkoutStatus.setStatus(CheckoutStatus.SUCCESS);
				checkoutStatus.setOrderDetails(currentOrder.getOrder_details());
				System.out
						.println("Order is : " + currentOrder.getId() + " Details: " + currentOrder.getOrder_details());
			} else {
				checkoutStatus.setOrderNumber("");
				checkoutStatus.setStatus(CheckoutStatus.FAILURE);
				checkoutStatus.setOrderDetails("Product is Out of Stock!");
			}
		} catch (NotEnoughProductsInStockException e) {
			checkoutStatus.setOrderNumber("");
			checkoutStatus.setStatus(CheckoutStatus.FAILURE);
			return new ResponseEntity<CheckoutStatus>(checkoutStatus, HttpStatus.OK);
		}
		return new ResponseEntity<CheckoutStatus>(checkoutStatus, HttpStatus.OK);
	}
}
