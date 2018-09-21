package io.pivotal.pad.cronos.service;

import java.util.Map;

import io.pivotal.pad.cronos.domain.Order;
import io.pivotal.pad.cronos.domain.ProductMetadata;
import io.pivotal.pad.cronos.exception.NotEnoughProductsInStockException;

public interface ShoppingCartService {

    void addProduct(String product);

    void removeProduct(String product);

    Map<String, Integer> getProductsInCart();

    Order checkout() throws NotEnoughProductsInStockException;

    Double getTotal();
}
