package com.h9drairawan.vanguard_backend.service;

import com.h9drairawan.vanguard_backend.dto.request.CategoryCreateRequest;
import com.h9drairawan.vanguard_backend.dto.request.CategoryUpdateRequest;
import com.h9drairawan.vanguard_backend.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();

    CategoryResponse createCategory(CategoryCreateRequest dto);

    CategoryResponse updateCategory(Long id, CategoryUpdateRequest dto);

    void deleteCategory(Long id);
}
