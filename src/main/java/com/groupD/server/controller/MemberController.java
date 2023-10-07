package com.groupD.server.controller;

import com.groupD.server.domain.dto.GroupRequest;
import com.groupD.server.domain.dto.GroupResponse;
import com.groupD.server.domain.entity.Group;
import com.groupD.server.security.Auth;
import com.groupD.server.security.AuthInfo;
import com.groupD.server.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/groups")
    public List<GroupResponse> getGroupsByMemberId(@Auth AuthInfo authInfo) {
        List<GroupResponse> groups = memberService.getGroupsByAuthInfo(authInfo);
        return groups;
    }
}

