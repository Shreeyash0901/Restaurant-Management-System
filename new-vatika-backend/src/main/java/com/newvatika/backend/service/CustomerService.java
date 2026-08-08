package com.newvatika.backend.service;

import com.newvatika.backend.dto.CustomerDto;
import com.newvatika.backend.entity.Order;
import com.newvatika.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final OrderRepository orderRepository;

    public List<CustomerDto> getAllCustomers() {

        List<Order> orders = orderRepository.findAll();

        Map<String, List<Order>> grouped =
                orders.stream()
                        .collect(Collectors.groupingBy(Order::getCustomerPhone));

        List<CustomerDto> customers = new ArrayList<>();

        for (Map.Entry<String, List<Order>> entry : grouped.entrySet()) {

            List<Order> customerOrders = entry.getValue();

            customerOrders.sort(
                    Comparator.comparing(Order::getCreatedAt).reversed()
            );

            Order latest = customerOrders.get(0);

            double totalSpent =
                    customerOrders.stream()
                            .mapToDouble(Order::getTotal)
                            .sum();

           customers.add(
    CustomerDto.builder()
            .customerName(latest.getCustomerName())
            .phone(latest.getCustomerPhone())
            .address(
                    (latest.getHouseNo() == null ? "" : latest.getHouseNo() + ", ")
                            + (latest.getStreet() == null ? "" : latest.getStreet() + ", ")
                            + (latest.getLandmark() == null ? "" : latest.getLandmark())
            )
            .totalOrders((long) customerOrders.size())
            .totalSpent(totalSpent)
            .lastOrderDate(latest.getCreatedAt().toString())
            .build()
);
        }

        customers.sort(
                Comparator.comparing(CustomerDto::getLastOrderDate).reversed()
        );

        return customers;
    }
}