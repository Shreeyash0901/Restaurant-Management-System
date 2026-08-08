package com.newvatika.backend.service;

import com.newvatika.backend.dto.ReportDashboardDto;
import com.newvatika.backend.entity.Order;
import com.newvatika.backend.entity.OrderStatus;
import com.newvatika.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportsService {

    private final OrderRepository orderRepository;

    public ReportDashboardDto getDashboardReport() {

        List<Order> orders = orderRepository.findAll();

        double revenue = orders.stream()
                .mapToDouble(Order::getTotal)
                .sum();

        long totalOrders = orders.size();

        double average =
                totalOrders == 0
                        ? 0
                        : revenue / totalOrders;
        
       long pending = orders.stream()
        .filter(order ->
                order.getStatus() == OrderStatus.PENDING ||
                order.getStatus() == OrderStatus.CONFIRMED ||
                order.getStatus() == OrderStatus.PREPARING ||
                order.getStatus() == OrderStatus.READY
        )
        .count();
        long completed =
                orderRepository.findByStatus(OrderStatus.COMPLETED).size();

        long cancelled =
                orderRepository.findByStatus(OrderStatus.CANCELLED).size();

        return ReportDashboardDto.builder()
                .totalRevenue(revenue)
                .totalOrders(totalOrders)
                .averageOrderValue(average)
                .pendingOrders(pending)
                .completedOrders(completed)
                .cancelledOrders(cancelled)
                .build();
    }
}