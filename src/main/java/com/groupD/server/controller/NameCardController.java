package com.groupD.server.controller;

import com.groupD.server.domain.dto.EditNameCardRequestDto;
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
}
