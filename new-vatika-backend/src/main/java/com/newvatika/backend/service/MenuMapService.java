package com.newvatika.backend.service;

import com.newvatika.backend.dto.MenuGroupResponse;
import com.newvatika.backend.entity.Category;
import com.newvatika.backend.entity.MenuItem;
import com.newvatika.backend.repository.CategoryRepository;
import com.newvatika.backend.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class MenuMapService {

    private final CategoryRepository categoryRepository;
    private final MenuItemRepository menuItemRepository;

    public Map<String, MenuGroupResponse> getGroupedMenu() {

        List<Category> categories = categoryRepository.findAll()
                .stream()
                .filter(Category::getActive)
                .sorted(Comparator.comparing(Category::getDisplayOrder))
                .toList();

        List<MenuItem> allItems = menuItemRepository.findAll();

        Map<String, MenuGroupResponse> result = new LinkedHashMap<>();

        for (Category category : categories) {

            List<MenuGroupResponse.MenuItemDto> items = allItems.stream()
                    .filter(item -> item.getCategory().getId().equals(category.getId()))
                    .filter(MenuItem::getIsAvailable)
                    .sorted(Comparator.comparing(MenuItem::getDisplayOrder))
                    .map(this::toItemDto)
                    .toList();

            MenuGroupResponse group = MenuGroupResponse.builder()
                    .icon(category.getIcon())
                    .label(MenuGroupResponse.LabelDto.builder()
                            .en(category.getNameEn())
                            .hi(category.getNameHi())
                            .build())
                    .items(items)
                    .build();

            result.put(category.getSlug(), group);
        }

        return result;
    }

    private MenuGroupResponse.MenuItemDto toItemDto(MenuItem item) {

        String priceText;
        if (item.getPriceFull() != null) {
            priceText = "₹" + formatPrice(item.getPriceHalf()) + "/" + formatPrice(item.getPriceFull());
        } else {
            priceText = "₹" + formatPrice(item.getPrice());
        }

        return MenuGroupResponse.MenuItemDto.builder()
                .name(MenuGroupResponse.LabelDto.builder()
                        .en(item.getNameEn())
                        .hi(item.getNameHi())
                        .build())
                .price(priceText)
                .build();
    }

    private String formatPrice(Double value) {
        if (value == null) return "0";
        return value == Math.floor(value) ? String.valueOf(value.intValue()) : String.valueOf(value);
    }
}