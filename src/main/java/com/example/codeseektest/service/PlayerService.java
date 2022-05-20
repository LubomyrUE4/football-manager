package com.example.codeseektest.service;

import com.example.codeseektest.domain.Player;
import com.example.codeseektest.exception.DefaultException;
import com.example.codeseektest.exception.PlayerAlreadyExistsException;
import com.example.codeseektest.exception.PlayerNotFoundException;
import com.example.codeseektest.repo.PlayerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepo playerRepo;

    public Player save(Player player) throws DefaultException {
        if((player.getId() != null && existsById(player.getId()))
                || (player.getName() != null && existsByName(player.getName()))) {
            throw new PlayerAlreadyExistsException();
        }
        return playerRepo.save(player);
    }

    public List<Player> findAll() {
        return playerRepo.findAll();
    }

    public Player findById(Long id) throws DefaultException {
        return playerRepo.findById(id).orElseThrow(PlayerNotFoundException::new);
    }

    public Player update(Player player) throws DefaultException {
        if((player.getId() == null && player.getName() == null)
                || (player.getId() != null && !existsById(player.getId()))
                || (player.getName() != null && !existsByName(player.getName()))) {
            throw new PlayerNotFoundException();
        }
        player.setId(findByName(player.getName()).getId());
        return playerRepo.save(player);
    }

    public void deleteById(Long id) throws DefaultException {
        if(!existsById(id)) {
            throw new PlayerNotFoundException();
        }
        playerRepo.deleteById(id);
    }

    public Player findByName(String name) throws DefaultException {
        if(playerRepo.findByName(name) == null) {
            throw new PlayerNotFoundException();
        }
        return playerRepo.findByName(name);
    }

    public boolean existsById(Long id) {
        return playerRepo.existsById(id);
    }

    public boolean existsByName(String name){
        return playerRepo.existsByName(name);
    }
}