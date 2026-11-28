package com.h9drairawan.vanguard_backend.mapper;

import com.h9drairawan.vanguard_backend.dto.request.CreateWarehouseDto;
import com.h9drairawan.vanguard_backend.dto.response.WarehouseDto;
import com.h9drairawan.vanguard_backend.entity.Warehouse;
import com.h9drairawan.vanguard_backend.util.CodeGeneratorUtils;

import java.time.LocalDateTime;

public class WarehouseMapper {
    public Warehouse toEntity(CreateWarehouseDto dto) {
        Warehouse warehouse = new Warehouse();
        LocalDateTime now = LocalDateTime.now();

        warehouse.setCode(CodeGeneratorUtils.generatePrefixedCode("WH", 6, true));
        warehouse.setName(dto.name());
        warehouse.setAddress(dto.address());
        warehouse.setActive(true);
        warehouse.setCreatedAt(now);
        warehouse.setUpdatedAt(now);
        return warehouse;
    }

    public WarehouseDto toDto(Warehouse entity) {
        return new WarehouseDto(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getAddress()
        );
    }
}
