package io.pivotal.pad.cronos.service;

import io.pivotal.pad.cronos.domain.Order;
import io.pivotal.pad.cronos.exception.NotEnoughProductsInStockException;

public interface CheckoutService {
	
    Order checkout(String userId) throws NotEnoughProductsInStockException;

}
