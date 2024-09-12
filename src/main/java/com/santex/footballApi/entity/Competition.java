package com.santex.footballApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "areaName")
    private String areaName;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Team> teams;
}