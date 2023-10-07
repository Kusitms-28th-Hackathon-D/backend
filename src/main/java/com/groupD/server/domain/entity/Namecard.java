package com.groupD.server.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "namecards")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Namecard{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "namecard_id")
    private Long id;

    // Namecard와 Member 간의 One-to-One 관계
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}