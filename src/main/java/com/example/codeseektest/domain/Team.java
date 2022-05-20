package com.example.codeseektest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tblTeams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long budget;
    private Integer commissionPercent;

    public Team(String name, Long budget, Integer commissionPercent) {
        this.name = name;
        this.budget = budget;
        this.commissionPercent = commissionPercent;
    }
}
