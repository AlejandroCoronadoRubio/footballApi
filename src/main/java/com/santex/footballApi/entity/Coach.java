package com.santex.footballApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "coach")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coach implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teamId")
    private Team team;
}
