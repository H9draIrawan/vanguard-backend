package com.h9drairawan.vanguard_backend.service.impl;

import com.h9drairawan.vanguard_backend.dto.request.CreateUserDto;
import com.h9drairawan.vanguard_backend.dto.request.UpdateUserDto;
import com.h9drairawan.vanguard_backend.dto.response.UserDto;
import com.h9drairawan.vanguard_backend.entity.User;
import com.h9drairawan.vanguard_backend.exception.DuplicateResourceException;
import com.h9drairawan.vanguard_backend.exception.ResourceNotFoundException;
import com.h9drairawan.vanguard_backend.mapper.UserMapper;
import com.h9drairawan.vanguard_backend.repository.UserRepository;
import com.h9drairawan.vanguard_backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page.map(userMapper::toDto);
    }

    @Override
    public UserDto createUser(CreateUserDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new DuplicateResourceException("User email " + dto.email() + " already exist");
        }

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));

        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    public UserDto updateUser(Long id, UpdateUserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));
        if (userRepository.existsByEmail(dto.email())) {
            throw new DuplicateResourceException("User email already exist");
        } else if (userRepository.existsByUsername(dto.username())) {
            throw new DuplicateResourceException("User username already exist");
        }

        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUserPassword(Long id, UpdateUserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

        user.setPassword(dto.password());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUserRole(Long id, UpdateUserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

        user.setRole(dto.role());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

        userRepository.deleteById(id);
    }
}
