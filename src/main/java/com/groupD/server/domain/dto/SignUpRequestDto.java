package com.groupD.server.domain.dto;

import com.groupD.server.domain.Disability;
import com.groupD.server.domain.PreferJob;
import com.groupD.server.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Role role;
    @NotBlank
    private String phonenum;

    @Enumerated(EnumType.STRING)
    private Disability disability;

    @Enumerated(EnumType.STRING)
    private PreferJob jobPriority1;

    @Enumerated(EnumType.STRING)
    private PreferJob jobPriority2;

    @Enumerated(EnumType.STRING)
    private PreferJob jobPriority3;

    private String imageUrl;

}
