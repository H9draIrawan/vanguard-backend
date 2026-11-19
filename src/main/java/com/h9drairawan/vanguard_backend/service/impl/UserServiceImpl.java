package com.h9drairawan.vanguard_backend.service.impl;

import com.h9drairawan.vanguard_backend.dto.request.UserRequest;
import com.h9drairawan.vanguard_backend.dto.response.UserResponse;
import com.h9drairawan.vanguard_backend.entity.Role;
import com.h9drairawan.vanguard_backend.entity.User;
import com.h9drairawan.vanguard_backend.exception.DuplicateResourceException;
import com.h9drairawan.vanguard_backend.mapper.RoleMapper;
import com.h9drairawan.vanguard_backend.mapper.UserMapper;
import com.h9drairawan.vanguard_backend.repository.UserRepository;
import com.h9drairawan.vanguard_backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page.map(userMapper::toResponse);
    }

    @Override
    public UserResponse createUser(UserRequest dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new DuplicateResourceException("Email " + dto.email() + " already exist");
        }

        Role role = roleMapper.toEntity(dto.roleRequest());
        User user = userMapper.toEntity(dto);

        user.setRole(role);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }
}
