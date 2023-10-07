package com.groupD.server.service;

import com.groupD.server.domain.dto.GroupRequest;
import com.groupD.server.domain.dto.GroupResponse;
import com.groupD.server.domain.entity.Group;
import com.groupD.server.domain.entity.Member;
import com.groupD.server.domain.entity.MemberGroup;
import com.groupD.server.repository.GroupRepository;
import com.groupD.server.repository.MemberGroupRepository;
import com.groupD.server.repository.MemberRepository;
import com.groupD.server.security.Auth;
import com.groupD.server.security.AuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberGroupRepository memberGroupRepository;
    private final GroupRepository groupRepository;


    public List<GroupResponse> getGroupsByAuthInfo(@Auth AuthInfo authInfo) {
        Member member = memberRepository.findByEmail(authInfo.getEmail()).orElseThrow(()-> new RuntimeException("aa"));
        List<MemberGroup> memberGroupList = memberGroupRepository.findAllByMember(member);
        List<Group> groupList = new ArrayList<>();
        for(MemberGroup memberGroup: memberGroupList) {
            Group group = memberGroup.getGroup();
            groupList.add(group);
        }

        List<GroupResponse> groupResponses = groupList.stream()
                .map(GroupResponse::new)
                .collect(Collectors.toList());

        return groupResponses;
    }
}

