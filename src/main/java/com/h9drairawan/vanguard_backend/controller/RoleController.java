package com.h9drairawan.vanguard_backend.controller;

import com.h9drairawan.vanguard_backend.dto.request.RoleCreateRequest;
import com.h9drairawan.vanguard_backend.dto.request.RoleUpdateRequest;
import com.h9drairawan.vanguard_backend.dto.response.RoleResponse;
import com.h9drairawan.vanguard_backend.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllRoles());
    }

    @PostMapping
    public ResponseEntity<RoleResponse> createRole(
            @Valid @RequestBody RoleCreateRequest request
    ) {
        RoleResponse response = service.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody RoleUpdateRequest request
    ) {
        RoleResponse response = service.updateRole(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
