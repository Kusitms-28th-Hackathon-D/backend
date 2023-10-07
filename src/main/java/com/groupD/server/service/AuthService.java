package com.groupD.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupD.server.domain.Role;
import com.groupD.server.domain.dto.KakaoProfileDto;
import com.groupD.server.domain.dto.ReissueResponseDto;
import com.groupD.server.domain.dto.SignInResponseDto;
import com.groupD.server.domain.entity.Member;
import com.groupD.server.exception.auth.*;
import com.groupD.server.repository.MemberRepository;
import com.groupD.server.security.jwt.JwtTokenProvider;
import com.groupD.server.security.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
    public void signUp(String email, String password, String username, Role role, String oauth, String image) {
        if(memberRepository.existsByEmail(email)) throw new EmailExistsException("이미 가입한 이메일입니다.");
        memberRepository.save(
                Member.builder()
                        .id(null)
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .username(username)
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
                member.getId(), member.getEmail(), member.getUsername(), member.getImage(), member.getRole(), accessToken.getToken(), refreshToken.getToken(), accessToken.getExpireTime(), refreshToken.getExpireTime()
        );
    }

    @Transactional
    public ReissueResponseDto reissue(String email, String refreshToken) {
        Member member = memberRepository.findByEmail(email).orElseThrow(()->new InvalidEmailException("회원정보가 존재하지 않습니다."));
        if(!member.getRefreshToken().equals(refreshToken)) {
            throw new RefreshTokenNotFoundException("리프레쉬 토큰에서 유저정보를 찾을 수 없습니다.");
        }

        TokenInfo newAccessToken = tokenProvider.createAccessToken(member.getEmail(), member.getRole());
        TokenInfo newRefreshToken = tokenProvider.createRefreshToken(member.getEmail(), member.getRole());
        return new ReissueResponseDto(
                newAccessToken.getToken(), newRefreshToken.getToken()
        );
    }

    @Transactional
    public void logout(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(()->new InvalidEmailException("회원정보가 존재하지 않습니다."));
        member.updateRefreshToken(null);
    }

    @Transactional
    public KakaoProfileDto getKakaoProfile(String token) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        KakaoProfileDto profile;

        try {
            profile = objectMapper.readValue(response.getBody(), KakaoProfileDto.class);
        } catch(JsonMappingException e) {
            throw new JsonException("Json Mapping 오류가 발생했습니다.");
        } catch(JsonProcessingException e) {
            throw new JsonException("Json Processing 오류가 발생했습니다.");
        }

        return profile;
    }

    @Transactional
    public SignInResponseDto kakaoAutoSignIn(KakaoProfileDto profile) {
        Member kakaoUser = Member.builder()
                .email(profile.getKakao_account().getEmail())
                .username(profile.getProperties().getNickname())
                .image(profile.getKakao_account().getProfile().getProfile_image_url())
                .password(KAKAO_SECRET_SERVER_PWD)
                .oauth("KAKAO")
                .build();

        boolean userAlreadyExist = memberRepository.existsByEmail(kakaoUser.getEmail());

        if(!userAlreadyExist) {
            log.info(profile.getKakao_account().getEmail()+": 기존 회원이 아니므로 자동 회원가입 후 로그인을 진행합니다.");
            signUp(kakaoUser.getEmail(), kakaoUser.getPassword(), kakaoUser.getUsername(), Role.USER, kakaoUser.getOauth(), kakaoUser.getImage());
        } else {
            log.info(profile.getKakao_account().getEmail()+": 기존 회원이므로 자동 로그인을 진행합니다.");
        }
        return signIn(kakaoUser.getEmail(), kakaoUser.getPassword());
    }
}
