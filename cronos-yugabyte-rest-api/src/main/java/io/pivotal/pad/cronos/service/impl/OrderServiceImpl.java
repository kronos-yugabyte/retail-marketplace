package io.pivotal.pad.cronos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.pivotal.pad.cronos.domain.Order;
import io.pivotal.pad.cronos.repo.OrderRepository;
import io.pivotal.pad.cronos.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

	@Override
	public Optional<Order> findOrderById(String id) {
		return orderRepository.findOrderById(id);
	}

	@Override
	public List<Order> findAllOrdersPageable() {

		return orderRepository.findAllOrdersPageable();
	}

}
