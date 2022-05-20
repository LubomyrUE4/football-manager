package com.example.codeseektest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamAlreadyExistsException extends RuntimeException {
    private String message;
}
