package com.groupD.server.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    // Namecard와 Keyword 간의 One-to-Many 관계
    @OneToMany(mappedBy = "namecard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Keyword> keywords = new ArrayList<>();

    // 다른 필드들을 추가할 수 있습니다.
}