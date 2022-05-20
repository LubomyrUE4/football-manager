package com.example.codeseektest.repo;

import com.example.codeseektest.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team, Long> {
    Team findByName(String name);
    boolean existsById(Long id);
    boolean existsByName(String name);
}
