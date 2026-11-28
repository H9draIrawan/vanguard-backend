package com.h9drairawan.vanguard_backend.service;

import com.h9drairawan.vanguard_backend.dto.response.WarehouseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {
    Page<WarehouseDto> getAllWarehouses(Pageable pageable);
}
