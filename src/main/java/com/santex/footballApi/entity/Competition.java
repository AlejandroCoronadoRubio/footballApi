package com.santex.footballApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "competition")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Competition implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "code")
    @JsonProperty("code")
    private String code;

    @Column(name = "areaName")
    @JsonIgnore
    private String areaName;

    /*
    @JsonProperty("area")
    public void setAreaNameFromJson(Map<String, String> area) {
        this.areaName = area.get("name");
    }
    */

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    @JsonProperty("teams")
    private List<Team> teams;
}