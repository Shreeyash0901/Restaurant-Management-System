package com.newvatika.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameEn;
    private String nameHi;
    private String slug;
    private String icon;
    private Integer displayOrder;

    @Enumerated(EnumType.STRING)
    private MenuTab tab;

    @Builder.Default
    private Boolean active = true;
}