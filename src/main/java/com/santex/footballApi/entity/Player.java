package com.santex.footballApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "player")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teamId")
    @JsonIgnore
    private Team team;
}
