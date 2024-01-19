package org.ait.competence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ResetUserPasswordDto {
    private String oldPassword;
    private String newPassword;
}
