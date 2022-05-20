package com.example.codeseektest.controller;

import com.example.codeseektest.exception.DefaultException;
import com.example.codeseektest.model.TransferForm;
import com.example.codeseektest.service.MainService;
import com.example.codeseektest.service.PlayerService;
import com.example.codeseektest.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final PlayerService playerService;
    private final TeamService teamService;
    private final MainService mainService;

    @PostMapping("/transfer")
    public ResponseEntity<?> makeTransfer(@RequestBody @Validated TransferForm transferForm) throws DefaultException {
        return new ResponseEntity<>(
                mainService.makeTransfer(playerService.findByName(transferForm.getPlayerName()),
                    teamService.findByName(transferForm.getNewTeamName())),
                HttpStatus.OK
        );
    }
}
