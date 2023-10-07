package com.groupD.server.controller;

import com.groupD.server.domain.dto.QuestionRequestDto;
import com.groupD.server.domain.dto.QuestionResponseDto;
import com.groupD.server.security.Auth;
import com.groupD.server.security.AuthInfo;
import com.groupD.server.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @ApiOperation("질문 리스트 불러오기")
    @GetMapping("/question")
    @ResponseStatus(HttpStatus.OK)
    public QuestionResponseDto getQuestions(@Auth AuthInfo authInfo) {
        return memberService.getQuestions(authInfo);
    }

    @ApiOperation("질문 리스트에 질문 추가하기")
    @PostMapping("/question")
    @ResponseStatus(HttpStatus.OK)
    public void getQuestions(@Auth AuthInfo authInfo, @Valid @RequestBody QuestionRequestDto dto) {
        memberService.addQuestion(authInfo, dto.getQuestion());
    }

}

