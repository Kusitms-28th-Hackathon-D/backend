package com.groupD.server.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "keywords")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Keyword{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    private String value;

    // Keyword와 Namecard 간의 Many-to-One 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "namecard_id")
    private Namecard namecard;

}