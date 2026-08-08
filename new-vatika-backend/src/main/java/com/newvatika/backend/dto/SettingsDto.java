package com.newvatika.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettingsDto {

    private String restaurantName;

    private String ownerName;

    private String phone;

    private String email;

    private String gst;

    private String address;

    private String openingTime;

    private String closingTime;

    private Boolean cashEnabled;

    private Boolean upiEnabled;

    private Boolean cardEnabled;

    private Boolean autoAcceptOrders;

    private Boolean allowCustomerNotes;

    private Boolean pickupEnabled;

    private Boolean deliveryEnabled;
}