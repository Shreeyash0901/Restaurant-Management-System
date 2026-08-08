package com.newvatika.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private String customerName;

    private String phone;

    private String address;

    private Long totalOrders;

    private Double totalSpent;

    private String lastOrderDate;
}