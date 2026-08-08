package com.newvatika.backend.dto;

import com.newvatika.backend.entity.OrderStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long id;
    private String customerName;
    private Double total;
    private OrderStatus status;
}
