package com.groupD.server.controller;

import com.groupD.server.domain.dto.SignInRequestDto;
import com.groupD.server.domain.dto.SignInResponseDto;
import com.groupD.server.domain.dto.SignUpRequestDto;
import com.groupD.server.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @ApiOperation("일반 회원 가입")
    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.OK)
    public void signUp(@Valid @RequestBody SignUpRequestDto dto) {
        authService.signUp(dto);
    }

    @ApiOperation("로그인")
    @PostMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponseDto signIn(@Valid @RequestBody SignInRequestDto dto) {
        return authService.signIn(dto.getEmail(), dto.getPassword());
    }
}
