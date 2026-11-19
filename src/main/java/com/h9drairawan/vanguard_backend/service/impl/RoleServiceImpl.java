package com.h9drairawan.vanguard_backend.service.impl;

import com.h9drairawan.vanguard_backend.dto.request.RoleRequest;
import com.h9drairawan.vanguard_backend.dto.response.RoleResponse;
import com.h9drairawan.vanguard_backend.entity.Role;
import com.h9drairawan.vanguard_backend.exception.DuplicateResourceException;
import com.h9drairawan.vanguard_backend.mapper.RoleMapper;
import com.h9drairawan.vanguard_backend.repository.RoleRepository;
import com.h9drairawan.vanguard_backend.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toResponse).toList();
    }

    @Override
    public RoleResponse createRole(RoleRequest dto) {
        if (roleRepository.existsByName(dto.name())) {
            throw new DuplicateResourceException("Role " + dto.name() + " already exist");
        }
        Role role = roleMapper.toEntity(dto);
        Role save = roleRepository.save(role);
        return roleMapper.toResponse(save);
    }
}
