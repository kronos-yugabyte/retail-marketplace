package io.pivotal.pad.cronos.exception;

import io.pivotal.pad.cronos.domain.ProductMetadata;
import io.pivotal.pad.cronos.domain.ProductInventory;

public class NotEnoughProductsInStockException extends Exception {

    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductsInStockException(ProductInventory productInventory, ProductMetadata product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getTitle(), productInventory.getQuantity()));
    }

}
