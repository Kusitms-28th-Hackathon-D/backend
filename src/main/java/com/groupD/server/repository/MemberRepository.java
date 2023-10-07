package com.groupD.server.repository;

import com.groupD.server.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Member> findByEmail(String email);
    Optional<Member> findById(Long id);
}
