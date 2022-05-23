package com.example.codeseektest.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TeamForm {
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotNull(message = "Budget should not be blank")
    private Long budget;
    @NotNull(message = "Commission percent should not be blank")
    @Min(value=0, message = "Commission percent should not be lower than 0")
    @Max(value=10, message = "Commission percent should not be higher than 10")
    private Integer commissionPercent;
}