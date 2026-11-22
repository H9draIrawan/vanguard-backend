package com.h9drairawan.vanguard_backend.service;

import com.h9drairawan.vanguard_backend.dto.request.RoleRequest;
import com.h9drairawan.vanguard_backend.dto.request.RoleRequest;
import com.h9drairawan.vanguard_backend.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();

    RoleResponse createRole(RoleRequest dto);

    RoleResponse updateRole(Long id, RoleRequest dto);
}
