package com.newvatika.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDashboardDto {

    private Double totalRevenue;

    private Long totalOrders;

    private Double averageOrderValue;

    private Long pendingOrders;

    private Long confirmedOrders;

    private Long preparingOrders;

    private Long readyOrders;

    private Long completedOrders;

    private Long cancelledOrders;
}