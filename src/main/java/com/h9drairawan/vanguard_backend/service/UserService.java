package com.h9drairawan.vanguard_backend.service;

import com.h9drairawan.vanguard_backend.dto.request.CreateUserDto;
import com.h9drairawan.vanguard_backend.dto.request.UpdateUserDto;
import com.h9drairawan.vanguard_backend.dto.response.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDto> getAllUsers(Pageable pageable);

    UserDto createUser(CreateUserDto dto);

    UserDto updateUser(Long id, UpdateUserDto dto);

    UserDto updateUserPassword(Long id, UpdateUserDto dto);

    UserDto updateUserRole(Long id, UpdateUserDto dto);

    void deleteUser(Long id);

    interface InventoryService {
    }
}
