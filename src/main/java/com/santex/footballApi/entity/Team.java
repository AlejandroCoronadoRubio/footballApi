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
@Table(name = "team")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tla")
    private String tla;

    @Column(name = "shortName")
    private String shortName;

    @Column(name = "areaName")
    @JsonIgnore
    private String areaName;

    @JsonProperty("area")
    public void setAreaNameFromJson(Map<String, String> area) {
        this.areaName = area.get("name");
    }

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @JsonProperty("squad")
    private List<Player> players;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competitionId")
    @JsonIgnore
    private Competition competition;
}
