package com.groupD.server.controller;

import com.groupD.server.domain.dto.*;
import com.groupD.server.security.Auth;
import com.groupD.server.security.AuthInfo;
import com.groupD.server.service.AuthService;
import io.swagger.annotations.ApiOperation;
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
        authService.signUp(dto.getEmail(), dto.getPassword(), dto.getUsername(), dto.getRole(), "IN_APP", null);
    }

    @ApiOperation("로그인")
    @PostMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponseDto signIn(@Valid @RequestBody SignInRequestDto dto) {
        return authService.signIn(dto.getEmail(), dto.getPassword());
    }

    @ApiOperation("액세스 토큰 재발급 - 헤더에 refreshToken 정보 포함하여 요청")
    @PostMapping("/reissue")
    public ReissueResponseDto reissue(@Auth AuthInfo authInfo) {
        return authService.reissue(authInfo.getEmail(), authInfo.getToken());
    }

    @ApiOperation("로그아웃 - 리프레쉬 토큰 삭제")
    @PostMapping("/logout")
    public void logout(@Auth AuthInfo authInfo) {
        System.out.println("AuthController1 - "+authInfo.getEmail());
        System.out.println("AuthController2 - "+authInfo.getToken());
        System.out.println("AuthController3 - "+authInfo.getRole());
        authService.logout(authInfo.getEmail());
    }

    @ApiOperation("카카오 회원 가입(로그인)")
    @PostMapping("/kakao")
    @ResponseStatus(HttpStatus.OK)
    public SignInResponseDto kakaoVerification(@RequestBody KakaoSignInRequestDto dto) {
        return authService.kakaoAutoSignIn(authService.getKakaoProfile(dto.getToken()));
    }
}
