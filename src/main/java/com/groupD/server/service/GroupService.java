package com.groupD.server.service;

import com.groupD.server.domain.dto.GroupRequest;
import com.groupD.server.domain.entity.Group;
import com.groupD.server.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    public void write(GroupRequest request){
        Group group = Group.builder()
        .id(request.getId())
                .code(request.getCode())
                .title(request.getTitle())
                .date(request.getDate())
                .category(request.getCategory())
                .manage(request.getManage())
                .content(request.getContent())
                .build();
groupRepository.save(group);
    }
}
