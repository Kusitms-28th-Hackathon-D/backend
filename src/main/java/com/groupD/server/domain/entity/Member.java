package com.groupD.server.domain.entity;

import com.groupD.server.domain.Disability;
import com.groupD.server.domain.PreferJob;
import com.groupD.server.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "members")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

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

    @NotBlank
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String refreshToken;
    private String oauth;


    public void updateRefreshToken(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
    }
}
