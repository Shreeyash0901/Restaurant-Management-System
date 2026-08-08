package com.newvatika.backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemResponse {

    private Long id;

    private String nameEn;
    private String nameHi;

    private String descriptionEn;
    private String descriptionHi;

    private Double price;

    private String imageUrl;

    private Boolean isVeg;
    private Boolean isAvailable;

    private Integer displayOrder;

    private Long categoryId;
    private String categoryName;
}