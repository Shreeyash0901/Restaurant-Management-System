package com.newvatika.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRequest {

    @NotBlank(message = "English name is required")
    @Size(max = 150, message = "Name cannot exceed 150 characters")
    private String nameEn;

    @NotBlank(message = "Hindi name is required")
    @Size(max = 150, message = "Name cannot exceed 150 characters")
    private String nameHi;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String descriptionEn;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String descriptionHi;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    private String imageUrl;

    @Builder.Default
    private Boolean isVeg = true;

    @Builder.Default
    private Boolean isAvailable = true;

    @Builder.Default
    private Integer displayOrder = 0;
}