package com.groupD.server.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PreferJob {
    ManagementandAdministration("경영 사무"),
    SalesandMarketing("영업 판매"),
    EducationandWelfare("교육 복지"),
    HealthcareandMedical("보건 의료 "),
    ArtsandSports("예술 스포츠 "),
    InstallationandMaintenance("설치 정비"),
    AgricultureForestryandFisheries("농림 어업" );

    private final String displayName;


    public String getDisplayName() {
        return displayName;
    }
}
