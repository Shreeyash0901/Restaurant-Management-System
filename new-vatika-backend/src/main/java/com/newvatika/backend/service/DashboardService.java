package com.newvatika.backend.service;

import com.newvatika.backend.dto.DashboardResponse;
import com.newvatika.backend.entity.OrderStatus;
import com.newvatika.backend.repository.CategoryRepository;
import com.newvatika.backend.repository.MenuItemRepository;
import com.newvatika.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;


    public DashboardResponse getStats() {

        long totalOrders = orderRepository.count();

        long pendingOrders =
                orderRepository.findByStatus(OrderStatus.PENDING)
                        .size();


        long completedOrders =
                orderRepository.findByStatus(OrderStatus.COMPLETED)
                        .size();


        return DashboardResponse.builder()
                .totalOrders(totalOrders)
                .pendingOrders(pendingOrders)
                .completedOrders(completedOrders)
                .totalRevenue(calculateRevenue())
                .totalMenuItems(menuItemRepository.count())
                .totalCategories(categoryRepository.count())
                .build();
    }


    private BigDecimal calculateRevenue() {

        return orderRepository.findAll()
                .stream()
                .filter(order ->
                        order.getStatus() == OrderStatus.COMPLETED)
                .map(order ->
                        BigDecimal.valueOf(order.getTotalAmount()))
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );
    }
}