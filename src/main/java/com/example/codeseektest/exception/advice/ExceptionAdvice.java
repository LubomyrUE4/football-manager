package com.example.codeseektest.exception.advice;

import com.example.codeseektest.exception.*;
import com.example.codeseektest.exception.response.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(DefaultException.class)
    @ResponseBody
    public ErrorResponse handleDefaultException(DefaultException e) {
        return new ErrorResponse(e.getStatus().value(), e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ErrorResponse handleValidationErrors(MethodArgumentNotValidException e) {
        return new ErrorResponse(403, e.getFieldError().getDefaultMessage() == null ?
                "Not valid method argument" : e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseBody
    public ErrorResponse handlePlayerNotFoundException(PlayerNotFoundException e) {
        return new ErrorResponse(404, e.getMessage() == null ? "Player not found" : e.getMessage());
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleTeamNotFoundException(TeamNotFoundException e) {
        return new ErrorResponse(404, e.getMessage() == null ? "Team not found" : e.getMessage());
    }

    @ExceptionHandler(PlayerAlreadyExistsException.class)
    @ResponseBody
    public ErrorResponse handlePlayerAlreadyExistsException(PlayerAlreadyExistsException e) {
        return new ErrorResponse(409, e.getMessage() == null ? "Player already exists" : e.getMessage());
    }

    @ExceptionHandler(TeamAlreadyExistsException.class)
    @ResponseBody
    public ErrorResponse handleTeamAlreadyExistsException(TeamAlreadyExistsException e) {
        return new ErrorResponse(409, e.getMessage() == null ? "Player already exists" : e.getMessage());
    }
}
