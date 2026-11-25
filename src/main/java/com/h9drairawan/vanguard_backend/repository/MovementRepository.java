package com.h9drairawan.vanguard_backend.repository;

import com.h9drairawan.vanguard_backend.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
}
