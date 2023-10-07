package com.groupD.server.security;

import com.groupD.server.domain.Role;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    //대상이 되는 파라미터를 정의
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        System.out.println("works1");
        return parameter.hasParameterAnnotation(Auth.class) && parameter.getParameterType() == AuthInfo.class;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        UserDetails authentication = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("resolver:"+webRequest.getHeader("Authentication"));
        System.out.println("resolver:"+authentication.getUsername());
        System.out.println("resolver:"+getRoles(authentication.getAuthorities()));
        return new AuthInfo(
                webRequest.getHeader("Authentication"),
                authentication.getUsername(),
                getRoles(authentication.getAuthorities())
        );
    }

    private List<Role> getRoles(Collection<? extends GrantedAuthority> authorities) {
        List<Role> roles = new ArrayList<>();
        for(GrantedAuthority role: authorities) {
            roles.add(Role.valueOf(role.getAuthority()));
        }
        return roles;
    }
}
