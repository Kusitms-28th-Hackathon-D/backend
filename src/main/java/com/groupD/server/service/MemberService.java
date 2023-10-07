package com.groupD.server.service;

import com.groupD.server.domain.dto.QuestionResponseDto;
import com.groupD.server.domain.entity.Member;
import com.groupD.server.domain.entity.Question;
import com.groupD.server.exception.member.MemberNotExistsException;
import com.groupD.server.repository.MemberRepository;
import com.groupD.server.repository.QuestionRepository;
import com.groupD.server.security.Auth;
import com.groupD.server.security.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public QuestionResponseDto getQuestions(AuthInfo authInfo) {
        Member member = memberRepository.findByEmail(
                authInfo.getEmail()).orElseThrow(()-> new MemberNotExistsException("멤버가 존재하지 않습니다.")
        );
        List<Question> lists = questionRepository.findAll();
        List<String> questions = new ArrayList<>();
        for(Question list: lists) {
            questions.add(list.getContent());
        }
        return new QuestionResponseDto(questions);
    }

    @Transactional
    public void addQuestion(AuthInfo authInfo, String question) {
        Member member = memberRepository.findByEmail(
                authInfo.getEmail()).orElseThrow(()-> new MemberNotExistsException("멤버가 존재하지 않습니다.")
        );
        questionRepository.save(new Question(null, question));
    }
}

