package com.example.codeseektest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamNotFoundException extends RuntimeException {
    private String message;
}
