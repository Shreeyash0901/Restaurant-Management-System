package com.newvatika.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private long totalOrders;

    private long pendingOrders;

    private long completedOrders;

    private BigDecimal totalRevenue;

    private long totalMenuItems;

    private long totalCategories;
}