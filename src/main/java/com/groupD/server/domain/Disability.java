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
    None("불편하지 않아요");
    private final String displayName;


    public String getDisplayName() {
        return displayName;
    }
}
