package com.groupD.server.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "namecards")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NameCard extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public Member member;

    @OneToMany(mappedBy = "nameCard", orphanRemoval = true)
    public List<Keyword> keyword;

}
