package com.groupD.server.controller;

import com.groupD.server.domain.dto.EditNameCardRequestDto;
import com.groupD.server.domain.dto.GetKeywordListResponseDto;
import com.groupD.server.domain.dto.GetNameCardResponseDto;
import com.groupD.server.domain.dto.SignUpRequestDto;
import com.groupD.server.security.Auth;
import com.groupD.server.security.AuthInfo;
import com.groupD.server.service.NameCardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/namecard")
public class NameCardController {

    private final NameCardService nameCardService;

    @ApiOperation("명함 수정하기")
    @PostMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public void editNameCard(@Auth AuthInfo authInfo, @Valid @RequestBody EditNameCardRequestDto dto) {
        nameCardService.editNameCard(authInfo, dto);
    }

    @ApiOperation("명함 정보 가져오기")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GetNameCardResponseDto getNameCard(@Auth AuthInfo authInfo) {
        return nameCardService.getNameCard(authInfo);
    }

    @ApiOperation("존재하는 모든 키워드 가져오기")
    @GetMapping("/keywords")
    @ResponseStatus(HttpStatus.OK)
    public GetKeywordListResponseDto getAllKeywords() {
        return nameCardService.getAllKeywords();
    }

}
