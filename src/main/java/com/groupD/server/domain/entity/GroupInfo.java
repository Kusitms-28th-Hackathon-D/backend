package com.groupD.server.domain.entity;

import com.groupD.server.domain.PreferJob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "groupinfo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupinfo_id")
    private Long id;

    private String name;

    private String center;

    private String duration;

    @Enumerated(EnumType.STRING)
    private PreferJob relatedJob;

    private String inviteCode;
}