package com.santex.footballApi.repository;

import com.santex.footballApi.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
