package com.groupD.server.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Disability {
    PhysicalDisability("지체 장애"),
    IntellectualDisability("시각 장애"),
    HearingImpairment("청각 장애"),
    SpeechDisorder("언어 장애"),
    CognitiveImpairment("지적 정애"),
    FacialDisability("안면 장애");
    private final String displayName;


    public String getDisplayName() {
        return displayName;
    }
}
