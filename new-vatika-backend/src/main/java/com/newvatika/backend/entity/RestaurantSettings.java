package com.newvatika.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurant_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Restaurant Info
    private String restaurantName;

    private String ownerName;

    private String phone;

    private String email;

    private String gst;

    @Column(length = 500)
    private String address;

    private String openingTime;

    private String closingTime;

    // Payment Methods
    private Boolean cashEnabled;

    private Boolean upiEnabled;

    private Boolean cardEnabled;

    // Order Preferences
    private Boolean autoAcceptOrders;

    private Boolean allowCustomerNotes;

    private Boolean pickupEnabled;

    private Boolean deliveryEnabled;
}