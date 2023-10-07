package com.groupD.server.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "member_group")
@Entity
@Getter
@Setter
public class MemberGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_group_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    // Getter, Setter, Constructor
}

