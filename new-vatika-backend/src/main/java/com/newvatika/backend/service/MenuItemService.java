package com.newvatika.backend.service;

import com.newvatika.backend.dto.MenuItemRequest;
import com.newvatika.backend.dto.MenuItemResponse;
import com.newvatika.backend.entity.Category;
import com.newvatika.backend.entity.MenuItem;
import com.newvatika.backend.exception.ResourceNotFoundException;
import com.newvatika.backend.repository.CategoryRepository;
import com.newvatika.backend.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;

    public List<MenuItemResponse> getAll() {
        return menuItemRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MenuItemResponse getById(Long id) {
        return toResponse(findEntityById(id));
    }

    public MenuItemResponse save(MenuItemRequest request) {
        Category category = findCategoryById(request.getCategoryId());

        MenuItem menuItem = MenuItem.builder()
                .nameEn(request.getNameEn())
                .nameHi(request.getNameHi())
                .descriptionEn(request.getDescriptionEn())
                .descriptionHi(request.getDescriptionHi())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .isVeg(request.getIsVeg())
                .isAvailable(request.getIsAvailable())
                .displayOrder(request.getDisplayOrder())
                .category(category)
                .build();

        return toResponse(menuItemRepository.save(menuItem));
    }

    public MenuItemResponse update(Long id, MenuItemRequest request) {
        MenuItem existing = findEntityById(id);
        Category category = findCategoryById(request.getCategoryId());

        existing.setNameEn(request.getNameEn());
        existing.setNameHi(request.getNameHi());
        existing.setDescriptionEn(request.getDescriptionEn());
        existing.setDescriptionHi(request.getDescriptionHi());
        existing.setPrice(request.getPrice());
        existing.setImageUrl(request.getImageUrl());
        existing.setIsVeg(request.getIsVeg());
        existing.setIsAvailable(request.getIsAvailable());
        existing.setDisplayOrder(request.getDisplayOrder());
        existing.setCategory(category);

        return toResponse(menuItemRepository.save(existing));
    }

    public void delete(Long id) {
        menuItemRepository.delete(findEntityById(id));
    }

    private MenuItem findEntityById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + id));
    }

    private Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
    }

    private MenuItemResponse toResponse(MenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .nameEn(menuItem.getNameEn())
                .nameHi(menuItem.getNameHi())
                .descriptionEn(menuItem.getDescriptionEn())
                .descriptionHi(menuItem.getDescriptionHi())
                .price(menuItem.getPrice())
                .imageUrl(menuItem.getImageUrl())
                .isVeg(menuItem.getIsVeg())
                .isAvailable(menuItem.getIsAvailable())
                .displayOrder(menuItem.getDisplayOrder())
                .categoryId(menuItem.getCategory().getId())
                .categoryName(menuItem.getCategory().getNameEn())
                .build();
    }
}