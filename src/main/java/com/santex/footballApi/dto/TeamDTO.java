package com.santex.footballApi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDTO {
    private Long id;
    private String name;
    private String tla;
    private String shortName;
    private String areaName;
    private String address;
    private List<PlayerDTO> players;
    private CoachDTO coach;
}
