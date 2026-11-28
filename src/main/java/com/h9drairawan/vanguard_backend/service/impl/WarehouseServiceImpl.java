package com.h9drairawan.vanguard_backend.service.impl;

import com.h9drairawan.vanguard_backend.dto.response.WarehouseDto;
import com.h9drairawan.vanguard_backend.entity.Warehouse;
import com.h9drairawan.vanguard_backend.mapper.WarehouseMapper;
import com.h9drairawan.vanguard_backend.repository.WarehouseRepository;
import com.h9drairawan.vanguard_backend.service.WarehouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public Page<WarehouseDto> getAllWarehouses(Pageable pageable) {
        Page<Warehouse> page = warehouseRepository.findAll(pageable);
        return page.map(warehouseMapper::toDto);
    }
}
