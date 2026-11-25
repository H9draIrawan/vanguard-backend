package com.h9drairawan.vanguard_backend.repository;

import com.h9drairawan.vanguard_backend.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
