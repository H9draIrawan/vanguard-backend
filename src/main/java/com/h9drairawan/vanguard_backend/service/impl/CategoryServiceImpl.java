package com.h9drairawan.vanguard_backend.service.impl;

import com.h9drairawan.vanguard_backend.dto.request.CategoryCreateRequest;
import com.h9drairawan.vanguard_backend.dto.request.CategoryUpdateRequest;
import com.h9drairawan.vanguard_backend.dto.response.CategoryResponse;
import com.h9drairawan.vanguard_backend.entity.Category;
import com.h9drairawan.vanguard_backend.exception.DuplicateResourceException;
import com.h9drairawan.vanguard_backend.exception.ResourceNotFoundException;
import com.h9drairawan.vanguard_backend.mapper.CategoryMapper;
import com.h9drairawan.vanguard_backend.repository.CategoryRepository;
import com.h9drairawan.vanguard_backend.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toResponse).toList();
    }

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest dto) {
        if (categoryRepository.existsByName(dto.name())) {
            throw new DuplicateResourceException("Category username " + dto.name() + " already exist");
        }

        Category category = categoryMapper.toEntity(dto);
        Category save = categoryRepository.save(category);
        return categoryMapper.toResponse(save);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryUpdateRequest dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"
                        ));

        if (categoryRepository.existsByName(dto.name())) {
            throw new DuplicateResourceException("Category already exist");
        }

        category.setName(dto.name());
        category.setDescription(dto.description());
        categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"
                        ));
        categoryRepository.deleteById(id);
    }
}
