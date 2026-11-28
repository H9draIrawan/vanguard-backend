package com.h9drairawan.vanguard_backend.controller;

import com.h9drairawan.vanguard_backend.dto.response.WarehouseDto;
import com.h9drairawan.vanguard_backend.entity.Warehouse;
import com.h9drairawan.vanguard_backend.service.WarehouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {
    private final WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllWarehouses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<WarehouseDto> warehousePage = service.getAllWarehouses(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("warehouses", warehousePage.getContent());
        response.put("currentPage", warehousePage.getNumber());
        response.put("totalItems", warehousePage.getTotalElements());
        response.put("totalPages", warehousePage.getTotalPages());
        response.put("hasNext", warehousePage.hasNext());
        response.put("hasPrevious", warehousePage.hasPrevious());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
