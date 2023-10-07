package com.groupD.server.service;

import com.groupD.server.domain.Disability;
import com.groupD.server.domain.PreferJob;
import com.groupD.server.domain.Role;
import com.groupD.server.domain.dto.SignInResponseDto;
import com.groupD.server.domain.dto.SignUpRequestDto;
import com.groupD.server.domain.entity.Member;
import com.groupD.server.exception.auth.EmailExistsException;
import com.groupD.server.exception.auth.InvalidEmailException;
import com.groupD.server.exception.auth.InvalidPasswordException;
import com.groupD.server.repository.MemberRepository;
import com.groupD.server.security.jwt.JwtTokenProvider;
import com.groupD.server.security.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Value("secretPwd_secretPwd_secretPwd_secretPwd")
    private String KAKAO_SECRET_SERVER_PWD;

    @Transactional
    public void signUp(SignUpRequestDto dto) {
        if(memberRepository.existsByEmail(dto.getEmail())) throw new EmailExistsException("이미 가입한 이메일입니다.");
        memberRepository.save(
                Member.builder()
                        .id(null)
                        .email(dto.getEmail())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .imageUrl(dto.getImageUrl())
                        .role(dto.getRole())
                        .disability(dto.getDisability())
                        .phonenum(dto.getPhonenum())
                        .jobPriority1(dto.getJobPriority1())
                        .jobPriority2(dto.getJobPriority2())
                        .jobPriority3(dto.getJobPriority3())
                        .refreshToken(null)
                        .oauth("IN_APP")
                        .build()
        );
    }

    @Transactional
    public SignInResponseDto signIn(String email, String password) {
        Member member = memberRepository.findByEmail(email).orElseThrow(()->new InvalidEmailException("회원정보가 존재하지 않습니다."));
        if(!passwordEncoder.matches(password, member.getPassword())) {
            if(member.getOauth().equals("KAKAO")) {
                throw new InvalidPasswordException("카카오 계정입니다. 카카오 로그인으로 시도해보세요.");
            }
            throw new InvalidPasswordException("잘못된 비밀번호입니다.");
        }

        TokenInfo accessToken = tokenProvider.createAccessToken(member.getEmail(), member.getRole());
        TokenInfo refreshToken = tokenProvider.createRefreshToken(member.getEmail(), member.getRole());
        member.updateRefreshToken(refreshToken.getToken());
        return new SignInResponseDto(
                member.getId(), member.getEmail(), member.getImageUrl(), member.getRole(), accessToken.getToken(), refreshToken.getToken(), accessToken.getExpireTime(), refreshToken.getExpireTime()
        );


    }
}
