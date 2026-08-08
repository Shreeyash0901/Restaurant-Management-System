package com.newvatika.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameEn;
    private String nameHi;
    private String descriptionEn;
    private String descriptionHi;

    private Double price;
    private Double priceHalf;
    private Double priceFull;

    private String imageUrl;
    private Boolean isVeg;
    private Boolean isAvailable;
    private Integer displayOrder;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}