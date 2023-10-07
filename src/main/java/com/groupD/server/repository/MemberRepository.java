package com.groupD.server.repository;

import com.groupD.server.domain.dto.GroupRequest;
import com.groupD.server.domain.entity.Group;
import com.groupD.server.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    //List<Group> findByEmail(String email);
    Optional<Member> findByEmail(String email);
    Optional<Member> findById(Long id);

}
