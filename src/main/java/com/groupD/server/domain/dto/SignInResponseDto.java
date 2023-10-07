package com.groupD.server.domain.dto;

import com.groupD.server.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private String image;
    private Role role;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenRemainTime;
    private Long refreshTokenRemainTime;
}
