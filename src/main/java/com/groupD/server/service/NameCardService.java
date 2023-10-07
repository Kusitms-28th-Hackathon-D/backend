package com.groupD.server.service;

import com.groupD.server.domain.dto.EditNameCardRequestDto;
import com.groupD.server.domain.dto.GetKeywordListResponseDto;
import com.groupD.server.domain.dto.GetNameCardResponseDto;
import com.groupD.server.domain.entity.Keyword;
import com.groupD.server.domain.entity.Member;
import com.groupD.server.domain.entity.NameCardKeyword;
import com.groupD.server.domain.entity.Namecard;
import com.groupD.server.exception.member.MemberNotExistsException;
import com.groupD.server.exception.namecard.KeywordNotExistsException;
import com.groupD.server.repository.KeywordRepository;
import com.groupD.server.repository.MemberRepository;
import com.groupD.server.repository.NameCardKeywordRepository;
import com.groupD.server.repository.NameCardRepository;
import com.groupD.server.security.AuthInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NameCardService {

    private final NameCardRepository nameCardRepository;
    private final MemberRepository memberRepository;
    private final KeywordRepository keywordRepository;
    private final NameCardKeywordRepository nameCardKeywordRepository;

    @Transactional
    public void createNameCard(Member member) {
        nameCardRepository.save(new Namecard(null, member));
    }

    @Transactional
    public void editNameCard(AuthInfo authInfo, EditNameCardRequestDto dto) {
        Member member = memberRepository.findByEmail(
                authInfo.getEmail()).orElseThrow(()-> new MemberNotExistsException("멤버가 존재하지 않습니다.")
        );
        Namecard nameCard = nameCardRepository.findByMember(member);

        nameCardKeywordRepository.deleteAllByNamecard(nameCard);
        for(String desc: dto.getKeywords()) {
            Keyword keyword = keywordRepository.findByValue(desc).orElseThrow(()-> new KeywordNotExistsException("키워드가 존재하지 않습니다"));
            nameCardKeywordRepository.save(new NameCardKeyword(null, nameCard, keyword));
        }
    }

    @Transactional
    public GetNameCardResponseDto getNameCard(AuthInfo authInfo) {
        Member member = memberRepository.findByEmail(
                authInfo.getEmail()).orElseThrow(()-> new MemberNotExistsException("멤버가 존재하지 않습니다.")
        );
        Namecard namecard = nameCardRepository.findByMember(member);
        List<String> jobPriorities = new ArrayList<>();
        jobPriorities.add(member.getJobPriority1().toString());
        jobPriorities.add(member.getJobPriority2().toString());
        jobPriorities.add(member.getJobPriority3().toString());

        List<NameCardKeyword> keywordList = nameCardKeywordRepository.findAllByNamecard(namecard);
        List<String> keywords = new ArrayList<>();
        for(NameCardKeyword nameCardKeyword: keywordList) {
            keywords.add(nameCardKeyword.getKeyword().getValue());
        }

        return new GetNameCardResponseDto(member.getEmail(), member.getDisability().toString(), jobPriorities, keywords);
    }


    @Transactional
    public GetKeywordListResponseDto getAllKeywords() {
        return new GetKeywordListResponseDto(keywordRepository.findAll().stream().map(k->k.getValue()).collect(Collectors.toList()));
    }
}
