package com.groupD.server.controller;

import com.groupD.server.domain.dto.GroupRequest;
import com.groupD.server.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/group")
@RestController
public class GroupController {

    private final GroupService groupService;
    @PostMapping("/write")
    public void write(@RequestBody GroupRequest request)  {
       groupService.write(request);
    }
}
