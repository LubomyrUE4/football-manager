package com.example.codeseektest.repo;

import com.example.codeseektest.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepo extends JpaRepository<Player, Long> {
    Player findByName(String name);
    boolean existsById(Long id);
    boolean existsByName(String name);
}
