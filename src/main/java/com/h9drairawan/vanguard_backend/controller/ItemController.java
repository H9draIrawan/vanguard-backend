package com.h9drairawan.vanguard_backend.controller;

import com.h9drairawan.vanguard_backend.dto.request.ItemCreateRequest;
import com.h9drairawan.vanguard_backend.dto.request.ItemUpdateRequest;
import com.h9drairawan.vanguard_backend.dto.response.ItemResponse;
import com.h9drairawan.vanguard_backend.dto.response.UserResponse;
import com.h9drairawan.vanguard_backend.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ItemResponse> itemPage = service.getAllItems(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("items", itemPage.getContent());
        response.put("currentPage", itemPage.getNumber());
        response.put("totalItems", itemPage.getTotalElements());
        response.put("totalPages", itemPage.getTotalPages());
        response.put("hasNext", itemPage.hasNext());
        response.put("hasPrevious", itemPage.hasPrevious());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(
            @Valid @RequestBody ItemCreateRequest request
    ) {
        ItemResponse response = service.createItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ItemResponse> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody ItemUpdateRequest request
    ) {
        ItemResponse response = service.updateItem(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long id
    ) {
        service.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}
