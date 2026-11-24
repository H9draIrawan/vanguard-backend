package com.h9drairawan.vanguard_backend.service.impl;

import com.h9drairawan.vanguard_backend.dto.request.RoleCreateRequest;
import com.h9drairawan.vanguard_backend.dto.request.RoleUpdateRequest;
import com.h9drairawan.vanguard_backend.dto.response.RoleResponse;
import com.h9drairawan.vanguard_backend.entity.Role;
import com.h9drairawan.vanguard_backend.exception.DuplicateResourceException;
import com.h9drairawan.vanguard_backend.exception.ResourceNotFoundException;
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
    public RoleResponse createRole(RoleCreateRequest dto) {
        if (roleRepository.existsByName(dto.name())) {
            throw new DuplicateResourceException("Role username " + dto.name() + " already exist");
        }
        Role role = roleMapper.toEntity(dto);
        Role save = roleRepository.save(role);
        return roleMapper.toResponse(save);
    }

    @Override
    public RoleResponse updateRole(Long id, RoleUpdateRequest dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"
                        ));
        if (roleRepository.existsByName(dto.name())) {
            throw new DuplicateResourceException("Role already exist");
        }

        role.setName(dto.name());
        role.setDescription(dto.description());
        roleRepository.save(role);
        return roleMapper.toResponse(role);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"
                        ));
        roleRepository.deleteById(id);
    }
}
