package com.h9drairawan.vanguard_backend.repository;

import com.h9drairawan.vanguard_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}
