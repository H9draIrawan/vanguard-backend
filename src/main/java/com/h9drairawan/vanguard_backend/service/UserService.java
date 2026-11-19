package com.h9drairawan.vanguard_backend.service;

import com.h9drairawan.vanguard_backend.dto.request.UserRequest;
import com.h9drairawan.vanguard_backend.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
}
