package com.example.codeseektest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tblPlayers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private Integer experienceInMonths;
    @ManyToOne
    @JoinColumn(name="team_id", nullable=false)
    private Team team;

    public Player(String name, Integer age, Integer experienceInMonths, Team team) {
        this.name = name;
        this.age = age;
        this.experienceInMonths = experienceInMonths;
        this.team = team;
    }
}