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
    private final RoleRepository repository;
    private final RoleMapper mapper;

    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public RoleResponse createRole(RoleRequest dto) {
        if (repository.existsByName(dto.name())) {
            throw new DuplicateResourceException("Role " + dto.name() + " already exist");
        }
        Role role = mapper.toEntity(dto);
        Role save = repository.save(role);
        return mapper.toResponse(save);
    }
}
