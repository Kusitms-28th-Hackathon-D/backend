package com.groupD.server.domain.dto;


import com.groupD.server.domain.PreferJob;
import com.groupD.server.domain.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String manage;
    private String date;
    @Enumerated(EnumType.STRING)
    private PreferJob category;
    private String code;

    public GroupRequest(Group group) {
this.id=group.getId();
this.category=group.getCategory();
this.code=group.getCode();
this.date=group.getDate();
this.manage=group.getManage();
this.content=group.getContent();
this.title=group.getTitle();
    }
}
