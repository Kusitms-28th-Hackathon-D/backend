package com.groupD.server.service;

import com.groupD.server.domain.dto.EditNameCardRequestDto;
import com.groupD.server.domain.entity.Keyword;
import com.groupD.server.domain.entity.Member;
import com.groupD.server.domain.entity.NameCard;
import com.groupD.server.exception.member.MemberNotExistsException;
import com.groupD.server.repository.KeywordRepository;
import com.groupD.server.repository.MemberRepository;
import com.groupD.server.repository.NameCardRepository;
import com.groupD.server.security.AuthInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NameCardService {

    private final NameCardRepository nameCardRepository;
    private final MemberRepository memberRepository;
    private final KeywordRepository keywordRepository;

    @Transactional
    public void createNameCard() {

    }

    @Transactional
    public void editNameCard(AuthInfo authInfo, EditNameCardRequestDto dto) {
        Member member = memberRepository.findByEmail(
                authInfo.getEmail()).orElseThrow(()-> new MemberNotExistsException("멤버가 존재하지 않습니다.")
        );
        NameCard nameCard = nameCardRepository.findByMember(member);

        for(String desc: dto.getKeywords()) {
            keywordRepository.save(new Keyword(null, desc, nameCard));
        }
    }
}
