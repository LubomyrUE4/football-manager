package com.example.codeseektest.config;

import com.example.codeseektest.domain.Player;
import com.example.codeseektest.domain.Team;
import com.example.codeseektest.service.PlayerService;
import com.example.codeseektest.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {
    private final PlayerService playerService;
    private final TeamService teamService;

    @Bean
    InitializingBean initializeDatabase() {
        return () -> {
            if(!playerService.existsByName("Ngolo Kante"))
                playerService.save(new Player("Ngolo Kante", 31, 164,
                        teamService.save(new Team("Chelsea", 10000000L, 7))));
            if(!playerService.existsByName("Harry Kane"))
                playerService.save(new Player("Harry Kane", 28, 158,
                        teamService.save(new Team("Tottenham", 300000L, 10))));
            if(!playerService.existsByName("Cristiano Ronaldo"))
                playerService.save(new Player("Cristiano Ronaldo", 37, 256,
                        teamService.save(new Team("Manchester United", 7000000L, 6))));
            if(!playerService.existsByName("Lionel Messi"))
                playerService.save(new Player("Lionel Messi", 34, 218,
                        teamService.save(new Team("PSG", 20000000L, 3))));
            if(!playerService.existsByName("Robert Lewandowski"))
                playerService.save(new Player("Robert Lewandowski", 33, 205,
                        teamService.save(new Team("Bayern", 9000000L, 3))));
        };
    }
}
