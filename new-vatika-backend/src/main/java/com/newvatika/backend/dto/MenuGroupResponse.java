package com.newvatika.backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuGroupResponse {

    private String icon;
    private LabelDto label;
    private List<MenuItemDto> items;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class LabelDto {
        private String en;
        private String hi;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class MenuItemDto {
        private LabelDto name;
        private String price;
    }
}