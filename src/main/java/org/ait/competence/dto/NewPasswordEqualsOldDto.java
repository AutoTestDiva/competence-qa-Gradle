package org.ait.competence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class NewPasswordEqualsOldDto {
    private String message;
}
