package com.example.codeseektest.service;

import com.example.codeseektest.domain.Player;
import com.example.codeseektest.domain.Team;
import com.example.codeseektest.exception.DefaultException;
import com.example.codeseektest.exception.PlayerNotFoundException;
import com.example.codeseektest.exception.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class MainService {
    private final PlayerService playerService;
    private final TeamService teamService;

    public Map<String, String> makeTransfer(Player player, Team newTeam) throws DefaultException {
        if(player == null) {
            throw new PlayerNotFoundException();
        }
        if(newTeam == null) {
            throw new TeamNotFoundException();
        }

        Team prevTeam = player.getTeam();
        if(prevTeam.equals(newTeam)) {
            throw new DefaultException("The player is already in that team", HttpStatus.FORBIDDEN);
        }

        int initialFee = (int) Math.round(player.getExperienceInMonths() * 100000.0 / player.getAge());
        int commission = (int) Math.round(prevTeam.getCommissionPercent() / 100.0 * initialFee);
        int transferFee = initialFee + commission;
        if(newTeam.getBudget() < transferFee) {
            throw new DefaultException("New team budget is too low", HttpStatus.FORBIDDEN);
        }
        prevTeam.setBudget(prevTeam.getBudget() + transferFee);
        newTeam.setBudget(newTeam.getBudget() - transferFee);
        player.setTeam(newTeam);

        player = playerService.update(player);
        prevTeam = teamService.update(prevTeam);
        newTeam = teamService.update(newTeam);

        Map<String, String> result = new LinkedHashMap<>();
        result.put("player", player.getName());
        result.put("prevTeam", prevTeam.getName());
        result.put("newTeam", newTeam.getName());
        result.put("transferFee", transferFee + "");
        return result;
    }
}
