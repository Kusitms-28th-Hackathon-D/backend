package com.groupD.server.repository;

import com.groupD.server.domain.entity.Member;
import com.groupD.server.domain.entity.MemberGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberGroupRepository extends JpaRepository<MemberGroup, Long> {
    public List<MemberGroup> findAllByMember(Member member);
}
