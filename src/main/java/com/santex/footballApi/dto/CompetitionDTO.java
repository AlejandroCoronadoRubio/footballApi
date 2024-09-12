package com.santex.footballApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompetitionDTO {

    private Long id;
    private String name;
    private String code;
    private String areaName;

    private List<TeamDTO> teams;
}
