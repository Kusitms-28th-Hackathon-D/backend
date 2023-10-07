package com.groupD.server.repository;

import com.groupD.server.domain.entity.Member;
import com.groupD.server.domain.entity.NameCardKeyword;
import com.groupD.server.domain.entity.Namecard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NameCardKeywordRepository extends JpaRepository<NameCardKeyword, Long> {
    public List<NameCardKeyword> findAllByNamecard(Namecard namecard);
    public void deleteAllByNamecard(Namecard namecard);
}
