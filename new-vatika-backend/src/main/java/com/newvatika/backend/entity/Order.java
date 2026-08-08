package com.newvatika.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String customerPhone;


    @Enumerated(EnumType.STRING)
    private OrderType orderType;


    // Delivery address — null when PICKUP
    private String houseNo;

    private String street;

    private String landmark;

    private String pincode;


    private String preferredTime;


    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;


    private String note;

    private Double total;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;


    @CreationTimestamp
    private LocalDateTime createdAt;


    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true
)
@JsonManagedReference
@Builder.Default
private List<OrderItem> items = new ArrayList<>();


    public Double getTotalAmount() {
    return total;
}

public void setTotalAmount(Double totalAmount) {
    this.total = totalAmount;
}
}