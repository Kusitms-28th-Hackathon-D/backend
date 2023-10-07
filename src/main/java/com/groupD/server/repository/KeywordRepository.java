package com.groupD.server.repository;

import com.groupD.server.domain.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findByValue(String value);
    List<Keyword> findAll();
}
