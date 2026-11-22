package com.h9drairawan.vanguard_backend.mapper;

import com.h9drairawan.vanguard_backend.dto.request.ItemCreateRequest;
import com.h9drairawan.vanguard_backend.dto.response.ItemResponse;
import com.h9drairawan.vanguard_backend.entity.Item;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ItemMapper {
    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    public ItemMapper(UserMapper userMapper, CategoryMapper categoryMapper) {
        this.userMapper = userMapper;
        this.categoryMapper = categoryMapper;
    }

    public Item toEntity(ItemCreateRequest dto) {
        Item entity = new Item();
        LocalDateTime now = LocalDateTime.now();

        entity.setName(dto.name());
        entity.setSku(dto.sku());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setStock(dto.stock());
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        return entity;
    }

    public ItemResponse toResponse(Item entity) {
        return new ItemResponse(
                entity.getId(),
                entity.getName(),
                entity.getSku(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock(),
                categoryMapper.toResponse(entity.getCategory()),
                userMapper.toResponse(entity.getCreatedBy()),
                userMapper.toResponse(entity.getUpdatedBy()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
