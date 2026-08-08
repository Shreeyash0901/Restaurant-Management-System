package com.newvatika.backend.service;

import com.newvatika.backend.entity.Order;
import com.newvatika.backend.entity.OrderStatus;
import com.newvatika.backend.exception.ResourceNotFoundException;
import com.newvatika.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public List<Order> getAll() {

        return orderRepository.findAll();
    }


    public Order getById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: " + id
                        )
                );
    }


    public Order placeOrder(Order order) {

        order.setStatus(OrderStatus.PENDING);

        if (order.getItems() != null) {

            order.getItems()
                    .forEach(item -> item.setOrder(order));
        }

        return orderRepository.save(order);
    }


    public Order updateStatus(Long id, OrderStatus status) {

        Order order = getById(id);

        order.setStatus(status);

        return orderRepository.save(order);
    }


    public void delete(Long id) {

        Order order = getById(id);

        orderRepository.delete(order);
    }
}