package com.groupD.server.security;

import com.groupD.server.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Data
public class AuthInfo {
    private String token;
    private String email;
    private List<Role> role;
}
