package com.example.codeseektest.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TransferForm {
    @NotBlank(message = "Player name should not be blank")
    private String playerName;
    @NotBlank(message = "New team name should not be blank")
    private String newTeamName;
}