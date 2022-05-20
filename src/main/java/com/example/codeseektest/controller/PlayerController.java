package com.example.codeseektest.controller;

import com.example.codeseektest.domain.Player;
import com.example.codeseektest.exception.DefaultException;
import com.example.codeseektest.model.PlayerForm;
import com.example.codeseektest.service.PlayerService;
import com.example.codeseektest.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<Player> savePlayer(@RequestBody @Validated PlayerForm playerForm) throws DefaultException {
        return new ResponseEntity<>(
                playerService.save(new Player(playerForm.getName(), playerForm.getAge(), playerForm.getExperienceInMonths(),
                        teamService.findById(playerForm.getTeamId()))),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(playerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) throws DefaultException {
        return new ResponseEntity<>(playerService.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody @Validated PlayerForm playerForm) throws DefaultException {
        return new ResponseEntity<>(
                playerService.update(new Player(playerForm.getName(), playerForm.getAge(), playerForm.getExperienceInMonths(),
                        teamService.findById(playerForm.getTeamId()))),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Player> deletePlayerById(@PathVariable Long id) throws DefaultException {
        playerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
