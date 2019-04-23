package io.pivotal.pad.cronos.service.impl;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import io.pivotal.pad.cronos.domain.Order;
import io.pivotal.pad.cronos.domain.ProductInventory;
import io.pivotal.pad.cronos.domain.ProductMetadata;
import io.pivotal.pad.cronos.exception.NotEnoughProductsInStockException;
import io.pivotal.pad.cronos.repo.ProductInventoryRepository;
import io.pivotal.pad.cronos.repo.ProductMetadataRepo;
import io.pivotal.pad.cronos.rest.clients.ShoppingCartRestClient;
import io.pivotal.pad.cronos.service.CheckoutService;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

	private final ShoppingCartRestClient shoppingCartRestClient;

	private final ProductMetadataRepo productRepository;

	private final ProductInventoryRepository productInventoryRepository;

	ProductInventory productInventory;
	ProductMetadata productDetails;

	@Autowired
	public CheckoutServiceImpl(ShoppingCartRestClient shoppingCartRestClient,
			ProductInventoryRepository productInventoryRepository, ProductMetadataRepo productRepository) {
		this.shoppingCartRestClient = shoppingCartRestClient;
		this.productInventoryRepository = productInventoryRepository;
		this.productRepository = productRepository;
	}

	@Autowired
	private CassandraOperations cassandraTemplate;

	@Override
	public Order checkout(String userId) throws NotEnoughProductsInStockException {
		Map<String, Integer> products = shoppingCartRestClient.getProductsInCart(userId);
		System.out.println("*** In Checkout products ***");
		StringBuilder updateCartpreparedStatement = new StringBuilder();
		updateCartpreparedStatement.append("BEGIN TRANSACTION");
		Order currentOrder = null;
		StringBuilder orderDetails = new StringBuilder();
		orderDetails.append("Customer bought these Items: ");

		if (products.size() != 0) {
			for (Map.Entry<String, Integer> entry : products.entrySet()) {
				// Refresh quantity for every product before checking
				System.out.println("*** Checking out product *** " + entry.getKey());
				productInventory = productInventoryRepository.findById(entry.getKey()).orElse(null);
				productDetails = productRepository.findById(entry.getKey()).orElse(null);
				if (productInventory.getQuantity() < entry.getValue())
					throw new NotEnoughProductsInStockException(productInventory, productDetails);

				updateCartpreparedStatement.append(" UPDATE product_inventory SET quantity = quantity - "
						+ entry.getValue() + " where asin = '" + entry.getKey() + "' ;");
				orderDetails.append(" Product: " + productDetails.getTitle() + ", Quantity: " + entry.getValue() + ";");
			}
			double orderTotal = getTotal(products);
			orderDetails.append(" Order Total is : " + orderTotal);
			currentOrder = createOrder(orderDetails.toString(), orderTotal);
			updateCartpreparedStatement
					.append(" INSERT INTO orders (order_id, user_id, order_details, order_time, order_total) VALUES ("
							+ "'" + currentOrder.getId() + "', " + "'1'" + ", '" + currentOrder.getOrder_details()
							+ "', '" + currentOrder.getOrder_time() + "'," + currentOrder.getOrder_total() + ");");
			updateCartpreparedStatement.append(" END TRANSACTION;");
			System.out.println("Statemet is " + updateCartpreparedStatement.toString());
			cassandraTemplate.getCqlOperations().execute(updateCartpreparedStatement.toString());
		}
		products.clear();

		return currentOrder;

	}

	private Double getTotal(Map<String, Integer> products) {
		double price = 0.0;
		for (Map.Entry<String, Integer> entry : products.entrySet()) {

			productInventory = productInventoryRepository.findById(entry.getKey()).orElse(null);
			productDetails = productRepository.findById(entry.getKey()).orElse(null);
			price = price + productDetails.getPrice() * entry.getValue();
		}
		return price;
	}

	private Order createOrder(String orderDetails, double orderTotal) {
		Order order = new Order();
		LocalDateTime currentTime = LocalDateTime.now();
		order.setId(UUID.randomUUID().toString());
		order.setUser_id(1);
		order.setOrder_details(orderDetails);
		order.setOrder_time(currentTime.toString());
		order.setOrder_total(orderTotal);
		return order;
	}

}
