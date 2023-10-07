package com.groupD.server.domain.entity;

import com.groupD.server.domain.PreferJob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group{
    @Id
    @Column(name ="group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String manage;
    @NotBlank
    private String date;
    @Enumerated(EnumType.STRING)
    private PreferJob category;
    @NotBlank
    private String code;


}
