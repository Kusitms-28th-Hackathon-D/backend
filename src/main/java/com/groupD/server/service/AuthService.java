package com.groupD.server.service;

import com.groupD.server.domain.Role;
import com.groupD.server.domain.dto.SignInResponseDto;
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
    public void signUp(String email, String password, Role role, String oauth, String image) {
        if(memberRepository.existsByEmail(email)) throw new EmailExistsException("이미 가입한 이메일입니다.");
        memberRepository.save(
                Member.builder()
                        .id(null)
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .image(image)
                        .role(role)
                        .refreshToken(null)
                        .oauth(oauth)
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
                member.getId(), member.getEmail(), member.getImage(), member.getRole(), accessToken.getToken(), refreshToken.getToken(), accessToken.getExpireTime(), refreshToken.getExpireTime()
        );
    }
}
