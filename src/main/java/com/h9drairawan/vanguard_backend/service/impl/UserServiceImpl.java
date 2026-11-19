package com.h9drairawan.vanguard_backend.service.impl;

import com.h9drairawan.vanguard_backend.dto.request.UserRequest;
import com.h9drairawan.vanguard_backend.dto.response.UserResponse;
import com.h9drairawan.vanguard_backend.entity.User;
import com.h9drairawan.vanguard_backend.exception.DuplicateResourceException;
import com.h9drairawan.vanguard_backend.mapper.UserMapper;
import com.h9drairawan.vanguard_backend.repository.UserRepository;
import com.h9drairawan.vanguard_backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserResponse createUser(UserRequest dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new DuplicateResourceException("Email " + dto.email() + " already exist");
        }

        User user = mapper.toEntity(dto);
        User saved = repository.save(user);
        return mapper.toResponse(saved);
    }
}
