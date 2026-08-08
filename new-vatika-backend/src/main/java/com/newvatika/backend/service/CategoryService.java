package com.newvatika.backend.service;

import com.newvatika.backend.dto.CategoryRequest;
import com.newvatika.backend.dto.CategoryResponse;
import com.newvatika.backend.entity.Category;
import com.newvatika.backend.exception.ResourceNotFoundException;
import com.newvatika.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CategoryResponse getById(Long id) {
        return toResponse(findEntityById(id));
    }

    public CategoryResponse save(CategoryRequest request) {
        Category category = Category.builder()
                .nameEn(request.getNameEn())
                .nameHi(request.getNameHi())
                .slug(request.getSlug())
                .icon(request.getIcon())
                .tab(request.getTab())
                .displayOrder(request.getDisplayOrder())
                .active(request.getActive())
                .build();

        return toResponse(categoryRepository.save(category));
    }

    public CategoryResponse update(Long id, CategoryRequest request) {
        Category existing = findEntityById(id);

        existing.setNameEn(request.getNameEn());
        existing.setNameHi(request.getNameHi());
        existing.setSlug(request.getSlug());
        existing.setIcon(request.getIcon());
        existing.setTab(request.getTab());
        existing.setDisplayOrder(request.getDisplayOrder());
        existing.setActive(request.getActive());

        return toResponse(categoryRepository.save(existing));
    }

    public void delete(Long id) {
        categoryRepository.delete(findEntityById(id));
    }

    private Category findEntityById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    private CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .nameEn(category.getNameEn())
                .nameHi(category.getNameHi())
                .slug(category.getSlug())
                .icon(category.getIcon())
                .tab(category.getTab())
                .displayOrder(category.getDisplayOrder())
                .active(category.getActive())
                .build();
    }
}