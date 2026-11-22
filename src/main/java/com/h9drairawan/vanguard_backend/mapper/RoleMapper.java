package com.h9drairawan.vanguard_backend.mapper;

import com.h9drairawan.vanguard_backend.dto.request.RoleRequest;
import com.h9drairawan.vanguard_backend.dto.response.RoleResponse;
import com.h9drairawan.vanguard_backend.entity.Role;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RoleMapper {
    public Role toEntity(RoleRequest dto) {
        Role entity = new Role();
        LocalDateTime now = LocalDateTime.now();

        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        return entity;
    }

    public RoleResponse toResponse(Role entity) {
        return new RoleResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
