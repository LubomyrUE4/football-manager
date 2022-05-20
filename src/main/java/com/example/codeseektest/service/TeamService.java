package com.example.codeseektest.service;

import com.example.codeseektest.domain.Team;
import com.example.codeseektest.exception.*;
import com.example.codeseektest.repo.TeamRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepo teamRepo;
    private final PlayerService playerService;

    public Team save(Team team) throws DefaultException {
        if((team.getId() != null && existsById(team.getId()))
                || (team.getName() != null && existsByName(team.getName()))) {
            throw new TeamAlreadyExistsException();
        }
        return teamRepo.save(team);
    }

    public List<Team> findAll() {
        return teamRepo.findAll();
    }

    public Team findById(Long id) throws DefaultException {
        return teamRepo.findById(id).orElseThrow(TeamNotFoundException::new);
    }

    public Team update(Team team) throws DefaultException {
        if((team.getId() == null && team.getName() == null)
                || (team.getId() != null && !existsById(team.getId()))
                || (team.getName() != null && !existsByName(team.getName()))) {
            throw new TeamNotFoundException();
        }
        team.setId(findByName(team.getName()).getId());
        return teamRepo.save(team);
    }

    public void deleteById(Long id) throws DefaultException {
        if(!existsById(id)) {
            throw new TeamNotFoundException();
        }
        playerService.findAll().stream().filter(p -> p.getTeam().getId() == id).forEach(player -> player.setTeam(
                findAll().stream().max(Comparator.comparingLong(Team::getBudget)).get()
        ));
        teamRepo.deleteById(id);
    }

    public Team findByName(String name) throws DefaultException {
        if(teamRepo.findByName(name) == null) {
            throw new TeamNotFoundException();
        }
        return teamRepo.findByName(name);
    }

    public boolean existsById(Long id) {
        return teamRepo.existsById(id);
    }

    public boolean existsByName(String name) {
        return teamRepo.existsByName(name);
    }
}