package com.groupD.server.repository;

import com.groupD.server.domain.entity.Member;
import com.groupD.server.domain.entity.NameCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NameCardRepository extends JpaRepository<NameCard, Long> {
    public NameCard findByMember(Member member);
}
