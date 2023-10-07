package com.groupD.server.repository;

import com.groupD.server.domain.entity.Keyword;
import com.groupD.server.domain.entity.Member;
import com.groupD.server.domain.entity.NameCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
