package com.h9drairawan.vanguard_backend.service.impl;

import com.h9drairawan.vanguard_backend.dto.request.UserRequest;
import com.h9drairawan.vanguard_backend.dto.response.UserResponse;
import com.h9drairawan.vanguard_backend.entity.User;
import com.h9drairawan.vanguard_backend.mapper.UserMapper;
import com.h9drairawan.vanguard_backend.repository.UserRepository;
import com.h9drairawan.vanguard_backend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User entity = userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(entity);
        return userMapper.toResponse(savedUser);
    }
}
