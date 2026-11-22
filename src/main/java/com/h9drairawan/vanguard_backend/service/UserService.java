package com.h9drairawan.vanguard_backend.service;

import com.h9drairawan.vanguard_backend.dto.request.UserCreateRequest;
import com.h9drairawan.vanguard_backend.dto.request.UserUpdateRequest;
import com.h9drairawan.vanguard_backend.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserResponse> getAllUsers(Pageable pageable);

    UserResponse createUser(UserCreateRequest dto);

    UserResponse updateUser(Long id, UserUpdateRequest dto);

    UserResponse updateUserPassword(Long id, UserUpdateRequest dto);
    
    UserResponse updateUserRole(Long id, UserUpdateRequest dto);
}
