package com.groupD.server.domain.dto;

import com.groupD.server.domain.Disability;
import com.groupD.server.domain.PreferJob;
import com.groupD.server.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetNameCardResponseDto {
    private String memberName;
    private String disabilities;
    private List<String> jobPriorities;
    private List<String> keywords;
}
