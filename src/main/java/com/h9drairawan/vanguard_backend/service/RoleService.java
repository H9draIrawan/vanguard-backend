package com.h9drairawan.vanguard_backend.service;

import com.h9drairawan.vanguard_backend.dto.request.RoleCreateRequest;
import com.h9drairawan.vanguard_backend.dto.request.RoleUpdateRequest;
import com.h9drairawan.vanguard_backend.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();

    RoleResponse createRole(RoleCreateRequest dto);

    RoleResponse updateRole(Long id, RoleUpdateRequest dto);
}
