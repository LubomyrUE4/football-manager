package com.example.codeseektest.controller;

import com.example.codeseektest.domain.Team;
import com.example.codeseektest.exception.DefaultException;
import com.example.codeseektest.model.TeamForm;
import com.example.codeseektest.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<Team> saveTeam(@RequestBody @Validated TeamForm teamForm) throws DefaultException {
        return new ResponseEntity<>(
                teamService.save(new Team(teamForm.getName(), teamForm.getBudget(), teamForm.getCommissionPercent())),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) throws DefaultException {
        return new ResponseEntity<>(teamService.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Team> updateTeam(@RequestBody @Validated TeamForm teamForm) throws DefaultException {
        return new ResponseEntity<>(
                teamService.update(new Team(teamForm.getName(), teamForm.getBudget(), teamForm.getCommissionPercent())),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Team> deleteTeamById(@PathVariable Long id) throws DefaultException {
        teamService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
