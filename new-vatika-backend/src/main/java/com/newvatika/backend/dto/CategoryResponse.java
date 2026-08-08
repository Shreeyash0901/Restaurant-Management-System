package com.newvatika.backend.dto;

import com.newvatika.backend.entity.MenuTab;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private Long id;
    private String nameEn;
    private String nameHi;
    private String slug;
    private String icon;
    private MenuTab tab;
    private Integer displayOrder;
    private Boolean active;
}