package com.newvatika.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportResponse {

    private Long totalOrders;

    private Double totalRevenue;

    private Long completedOrders;

    private Long cancelledOrders;

    private Long pendingOrders;

    private Double averageOrderValue;
}