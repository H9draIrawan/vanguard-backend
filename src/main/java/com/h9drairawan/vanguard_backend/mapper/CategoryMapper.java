package com.h9drairawan.vanguard_backend.mapper;

import com.h9drairawan.vanguard_backend.dto.request.CategoryCreateRequest;
import com.h9drairawan.vanguard_backend.dto.response.CategoryResponse;
import com.h9drairawan.vanguard_backend.entity.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryCreateRequest dto) {
        Category entity = new Category();
        LocalDateTime now = LocalDateTime.now();

        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        return entity;
    }

    public CategoryResponse toResponse(Category entity) {
        return new CategoryResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
