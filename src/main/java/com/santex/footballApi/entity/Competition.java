package com.santex.footballApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "competition")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Competition implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @JsonProperty("competition")
    public void setCompetitionPropertiesFromJson(Map<String, String> competition) {
        this.id = Long.parseLong(competition.get("id"));
        this.name = competition.get("name");
        this.code = competition.get("code");
    }

    @Column(name = "areaName")
    private String areaName;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Team> teams;
}