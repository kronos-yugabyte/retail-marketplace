package io.pivotal.pad.cronos.service;

import java.util.List;

import java.util.Optional;
import java.util.UUID;

import io.pivotal.pad.cronos.domain.Order;

public interface OrderService {
	
	Optional<Order> findOrderById(String id);
	
	List<Order> findAllOrdersPageable();

}
