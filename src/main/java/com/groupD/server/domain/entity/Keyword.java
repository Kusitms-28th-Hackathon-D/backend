package com.groupD.server.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "keywords")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Keyword extends DateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword")
    public NameCard nameCard;
}
