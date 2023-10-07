package com.groupD.server.dto;

import com.groupD.server.domain.Sample;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleResponse {
    private Long id;
    private String photoUrl;

public SampleResponse( Sample sample){
    this.id =sample.getId();
    this.photoUrl=sample.getPhotoUrl();
}
}
