package com.h9drairawan.vanguard_backend.controller;

import com.h9drairawan.vanguard_backend.dto.request.CategoryCreateRequest;
import com.h9drairawan.vanguard_backend.dto.request.CategoryUpdateRequest;
import com.h9drairawan.vanguard_backend.dto.response.CategoryResponse;
import com.h9drairawan.vanguard_backend.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryCreateRequest request
    ) {
        CategoryResponse response = service.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryUpdateRequest request
    ) {
        CategoryResponse response = service.updateCategory(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long id
    ) {
        service.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
