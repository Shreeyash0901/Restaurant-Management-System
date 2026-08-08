package com.newvatika.backend.dto;

import com.newvatika.backend.entity.MenuTab;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "English name is required")
    private String nameEn;

    @NotBlank(message = "Hindi name is required")
    private String nameHi;

    @NotBlank(message = "Slug is required")
    private String slug;

    private String icon;

    @NotNull(message = "Tab is required")
    private MenuTab tab;

    @Builder.Default
    private Integer displayOrder = 0;

    @Builder.Default
    private Boolean active = true;
}