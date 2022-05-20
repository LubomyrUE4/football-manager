package com.example.codeseektest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class DefaultException extends RuntimeException {
    private String message;
    private HttpStatus status;
}
