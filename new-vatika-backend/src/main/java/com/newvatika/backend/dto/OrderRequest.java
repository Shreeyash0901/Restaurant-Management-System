package com.newvatika.backend.dto;

import com.newvatika.backend.entity.OrderType;
import com.newvatika.backend.entity.PaymentMethod;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    private String customerName;
    private String customerPhone;

    private OrderType orderType;

    private String houseNo;
    private String street;
    private String landmark;
    private String pincode;

    private String preferredTime;

    private PaymentMethod paymentMethod;

    private String note;
}