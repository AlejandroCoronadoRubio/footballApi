package com.santex.footballApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamMemberDTO {
    private Long id;
    private String name;
    private String dateOfBirth;
    private String nationality;
}