package com.groupD.server.repository;

import com.groupD.server.domain.entity.Member;
import com.groupD.server.domain.entity.Namecard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NameCardRepository extends JpaRepository<Namecard, Long> {
    public Namecard findByMember(Member member);
}
