package com.newvatika.backend.repository;

import com.newvatika.backend.entity.Order;
import com.newvatika.backend.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(OrderStatus status);
    List<Order> findByCustomerPhone(String phone);
}