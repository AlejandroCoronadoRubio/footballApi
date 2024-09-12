package com.santex.footballApi.repository;

import com.santex.footballApi.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamCompetitionCode(String code);
}
