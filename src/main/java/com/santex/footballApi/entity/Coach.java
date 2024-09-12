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
@Table(name = "coach")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coach implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teamId")
    @JsonIgnore
    private Team team;
}
