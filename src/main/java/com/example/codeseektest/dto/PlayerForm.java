package com.example.codeseektest.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PlayerForm {
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotNull(message = "Age should not be blank")
    @Min(value=1, message = "Age should be higher than 0")
    private Integer age;
    @NotNull(message = "Experience should not be blank")
    @Min(value=0, message = "Experience should not be lower than 0")
    private Integer experienceInMonths;
    @NotNull(message = "Team id should not be blank")
    private Long teamId;
}
